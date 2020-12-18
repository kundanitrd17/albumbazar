package com.albumbazaar.albumbazar.services;

import java.io.IOException;
import java.util.List;

import com.albumbazaar.albumbazar.model.OrderDetail;

import org.springframework.web.multipart.MultipartFile;

public interface GoogleDriveService {

    /**
     * Determine whether the user is authenticated or not
     * 
     * @param userId unique key from the google OAuth
     * @return boolean value to state the authentication of current user
     */
    public boolean isAuthenticatedToGoogle(String userId);

    /**
     * Url that the user needs to get redirect in order to be authenticated
     * 
     * @return URL to be redirected to for google OAuth sign
     */
    public String getRedirectUrlForGoogleSignIn();

    /**
     * Save the code received from the Google Authorization server
     * 
     * @param code from the callback
     */
    public void saveGoogleAuthorizationCode(String code, String userId) throws Exception;

    /**
     * Create folder with the particular name in google drive
     * 
     * @param folderName name of the folder to be created
     * @param userId     userid of the user whose drive's access is needed
     */
    public void createFolderAndMakePublic(String folderName, String userId, OrderDetail orderDetail) throws Exception;

    /**
     * upload Multipart files to the google drive
     * 
     * @param files               list of multipart files that needs to uploaded to
     *                            the drive
     * @param FOLDER_TO_UPLOAD_ID id of the parent folder in which the multipart
     *                            files needs to get uploaded * @param userId user
     *                            identification key to authorization
     */
    public void uploadToGoogleDrive(List<MultipartFile> files, String FOLDER_TO_UPLOAD_ID, String userId)
            throws IOException;

}
