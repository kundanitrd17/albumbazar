package com.albumbazaar.albumbazar.services.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import com.albumbazaar.albumbazar.dao.GoogleCredentialRepository;
import com.albumbazaar.albumbazar.dao.OrderRepository;
import com.albumbazaar.albumbazar.googledrivedatastore.JPADataStoreFactory;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("googleDriveService")
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final Logger logger = LoggerFactory.getLogger(GoogleDriveServiceImpl.class);

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    // private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE,
    // "https://www.googleapis.com/auth/drive.install");

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URI;

    @Value("${google.secret.key.path}")
    private Resource gdSecretKeys;

    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    private GoogleAuthorizationCodeFlow flow;

    private final GoogleCredentialRepository repository;
    private final OrderRepository orderRepository;

    // Contructor injecting google credential repository
    @Autowired(required = true)
    protected GoogleDriveServiceImpl(final GoogleCredentialRepository repository,
            final OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        // load client secrets
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(gdSecretKeys.getInputStream()));

        // set up authorization code flow
        // set data store factory
        DataStoreFactory dataStore = new JPADataStoreFactory(repository);

        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, secrets, SCOPES)
                .setDataStoreFactory(dataStore).build();
    }

    @Override
    public boolean isAuthenticatedToGoogle(String USER_IDENTIFIER_KEY) {

        boolean isUserAuthenticated = false;

        try {
            Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
            if (credential != null) {
                boolean tokenValid = credential.refreshToken();
                if (tokenValid) {
                    isUserAuthenticated = true;
                }
            }
            // System.out.println(flow.getCredentialDataStore().get(USER_IDENTIFIER_KEY));
            // System.out.println(USER_IDENTIFIER_KEY);
            // System.out.println(credential.getAccessToken());

        } catch (Exception e) {
            logger.error(e.getMessage());
            isUserAuthenticated = false;
        }

        return isUserAuthenticated;
    }

    @Override
    public String getRedirectUrlForGoogleSignIn() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        final String redirectURL = url.setRedirectUri(CALLBACK_URI).setAccessType("offline").build();

        return redirectURL;
    }

    @Override
    public void saveGoogleAuthorizationCode(String code, String userId) throws Exception {
        if (code != null) {
            saveToken(code, userId);
        }
    }

    private void saveToken(String code, String USER_IDENTIFIER_KEY) throws Exception {

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(CALLBACK_URI).execute();
        flow.createAndStoreCredential(response, USER_IDENTIFIER_KEY);

        // System.out.println(flow.getCredentialDataStore().get(USER_IDENTIFIER_KEY));
        // System.out.println(code);

    }

    /**
     * create a new folder on google drive and make it public
     * 
     * @param foldername  the name of the folder by which the folder is going to get
     *                    created
     * @param userId      user identifier key
     * @param orderDetail OrderDetail entity to set the file id into it
     * @throws Exception throws exception if anything goes wrong that would invoke a
     *                   rollback
     */
    @Override
    public void createFolderAndMakePublic(final String folderName, final String userId, final OrderDetail orderDetail)
            throws Exception {

        // Check whether if the orderEntity is missing
        if (orderDetail == null) {
            throw new RuntimeException("unable to find order info");
        }

        // If the folder name is not provided then terminate
        if (folderName == null) {
            throw new RuntimeException("provide a proper folder name");
        }

        // load the credential from the database and check if the user is authorized
        Credential credential = flow.loadCredential(userId);
        if (credential == null) {
            throw new RuntimeException("user unauthorized");
        }

        // create a drive object with required parameters and credentials of the user
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Quickstart")
                .build();

        // Create a new file using google drive api
        final File file = new File();
        file.setName(folderName);
        file.setMimeType("application/vnd.google-apps.folder");

        final File createdFolder = drive.files().create(file).setFields("id, webViewLink").execute();

        logger.info("file created with id: " + createdFolder.getId());

        try {
            // Set fileid attribute in the order detail entity
            orderDetail.setPhotoFolderGoogleDriveId(createdFolder.getId());
            orderDetail.setPhotoFolderGoogleDriveLink(createdFolder.getWebViewLink());

            /**
             * make the created folder public (Accessable by anyone using the webViewLink)
             */
            makeFolderPublic(orderDetail.getPhotoFolderGoogleDriveId(), drive);

            orderRepository.save(orderDetail);
        } catch (Exception e) {
            /**
             * Delete the created folder in case of any errors while changing the permission
             * of the file or setting updating fileId or webViewlink in the DB
             */
            deleteAnyFileOnGoogleDrive(drive, createdFolder.getId());
        }
    }

    /**
     * Change permission of a folder on google drive and make it public
     * 
     * @param fileId id of the file whose permission needs to be set public
     * @param drive  drive object using which all the operations needs to be done
     * @throws IOException
     */
    private void makeFolderPublic(final String fileId, final Drive drive) throws IOException {

        // Change the permission of the created folder
        final Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("writer");

        // returns a permission object which can be used later to change the permission
        drive.permissions().create(fileId, permission).execute();

    }

    @Override
    public void uploadToGoogleDrive(final List<MultipartFile> filesToUpload, final String FOLDER_TO_UPLOAD_ID,
            final String userId) throws IOException {

        final Credential credential = flow.loadCredential(userId);
        if (credential == null) {
            throw new RuntimeException("unable to authorize");
        }

        final Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Quickstart")
                .build();

        final AtomicInteger totalExceptionsThrown = new AtomicInteger();
        filesToUpload.parallelStream().forEach(fileToUpload -> {
            try {
                uploadToGoogleDrive(fileToUpload, FOLDER_TO_UPLOAD_ID, drive);
            } catch (Exception e) {
                totalExceptionsThrown.incrementAndGet();
                logger.error(e.getMessage());
            }
        });

        if (totalExceptionsThrown.get() >= filesToUpload.size()) {
            throw new RuntimeException("unable to upload files... please recreate folder");
        }

        logger.info("uploaded files to Google Drive");

    }

    /**
     * Helper function to upload a multipart file to Google drive
     * 
     * @param fileToUpload        the multipart file that needs to get uploaded
     * @param FOLDER_TO_UPLOAD_ID id of the folder in which the photos needs to get
     *                            stored
     * @param drive               Drive variable created using the GoogleFlow object
     * @throws IOException
     */
    private void uploadToGoogleDrive(final MultipartFile fileToUpload, final String FOLDER_TO_UPLOAD_ID,
            final Drive drive) throws IOException {

        final File file = new File();
        file.setName(fileToUpload.getOriginalFilename());
        file.setParents(Arrays.asList(FOLDER_TO_UPLOAD_ID));

        //////////////////////////////////////////////////////////////////////////
        // Create an object of ByteArrayContent using byte[] of MultipartFile that needs
        ////////////////////////////////////////////////////////////////////////// to be
        ////////////////////////////////////////////////////////////////////////// uploaded

        ByteArrayContent content = new ByteArrayContent(fileToUpload.getContentType(), fileToUpload.getBytes());
        // ByteArrayInputStream content = null;

        /**
         * We can get information about the uploaded file by using setFields function
         * eg. setFields("id,webViewLink,thumnaillink") For that you need to store the
         * returned value of the execute function in a FILE object
         */
        drive.files().create(file, content).execute();

        //////////////////////////////////////////////////////////////////////////////////////

    }

    private void deleteAnyFileOnGoogleDrive(final Drive drive, final String fileId) throws IOException {
        drive.files().delete(fileId).execute();
    }

}
