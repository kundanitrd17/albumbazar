package com.albumbazaar.albumbazar.services.impl;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.albumbazaar.albumbazar.dao.GoogleCredentialRepository;
import com.albumbazaar.albumbazar.googledrivedatastore.JPADataStoreFactory;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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

    // Contructor injecting google credential repository
    @Autowired(required = true)
    protected GoogleDriveServiceImpl(final GoogleCredentialRepository repository) {
        this.repository = repository;
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
        Credential cred = flow.createAndStoreCredential(response, USER_IDENTIFIER_KEY);

        // System.out.println(flow.getCredentialDataStore().get(USER_IDENTIFIER_KEY));
        // System.out.println(code);

    }

}
