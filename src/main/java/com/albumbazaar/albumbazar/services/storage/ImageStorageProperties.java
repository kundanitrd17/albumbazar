package com.albumbazaar.albumbazar.services.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class ImageStorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "test";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}