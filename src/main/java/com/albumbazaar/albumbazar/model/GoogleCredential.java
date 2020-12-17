package com.albumbazaar.albumbazar.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.api.client.auth.oauth2.StoredCredential;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "google_credential")
public class GoogleCredential {
    @Id
    private String keyId;
    private String accessToken;
    private Long expirationTimeMilliseconds;
    private String refreshToken;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public GoogleCredential(String keyId, StoredCredential credential) {
        this.keyId = keyId;
        this.accessToken = credential.getAccessToken();
        this.expirationTimeMilliseconds = credential.getExpirationTimeMilliseconds();
        this.refreshToken = credential.getRefreshToken();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void apply(StoredCredential credential) {
        this.accessToken = credential.getAccessToken();
        this.expirationTimeMilliseconds = credential.getExpirationTimeMilliseconds();
        this.refreshToken = credential.getRefreshToken();
        this.updatedAt = Instant.now();
    }

    public GoogleCredential() {
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpirationTimeMilliseconds() {
        return expirationTimeMilliseconds;
    }

    public void setExpirationTimeMilliseconds(Long expirationTimeMilliseconds) {
        this.expirationTimeMilliseconds = expirationTimeMilliseconds;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}