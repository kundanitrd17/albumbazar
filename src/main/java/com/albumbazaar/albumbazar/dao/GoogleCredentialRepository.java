package com.albumbazaar.albumbazar.dao;

import java.util.Optional;
import java.util.Set;

import com.albumbazaar.albumbazar.model.GoogleCredential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoogleCredentialRepository extends JpaRepository<GoogleCredential, String> {
    Optional<GoogleCredential> findByKeyId(String key);

    Optional<GoogleCredential> findByAccessToken(String key);

    @Query(value = "select key_id from google_credential", nativeQuery = true)
    Set<String> findAllKeys();
}