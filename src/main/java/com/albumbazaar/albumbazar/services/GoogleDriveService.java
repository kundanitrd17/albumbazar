package com.albumbazaar.albumbazar.services;

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

}
