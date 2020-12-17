package com.albumbazaar.albumbazar.googledrivedatastore;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.GoogleCredentialRepository;
import com.albumbazaar.albumbazar.model.GoogleCredential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.api.client.util.store.DataStore;

import org.springframework.beans.factory.annotation.Autowired;

public class JPADataStore extends AbstractDataStore<StoredCredential> {
    private GoogleCredentialRepository repository;
    private JPADataStoreFactory jpaDataStoreFactory;

    /**
     * @param dataStoreFactory data store factory
     * @param id               data store ID
     */
    @Autowired(required = true)
    protected JPADataStore(JPADataStoreFactory dataStoreFactory, String id, GoogleCredentialRepository repository) {
        super(dataStoreFactory, id);
        this.repository = repository;
    }

    @Override
    public JPADataStoreFactory getDataStoreFactory() {
        return jpaDataStoreFactory;
    }

    @Override
    public int size() throws IOException {
        return (int) repository.count();
    }

    @Override
    public boolean isEmpty() throws IOException {
        return size() == 0;
    }

    @Override
    public boolean containsKey(String key) throws IOException {
        return repository.findByKeyId(key).isPresent();
    }

    @Override
    public boolean containsValue(StoredCredential value) throws IOException {
        return repository.findByAccessToken(value.getAccessToken()).isPresent();
    }

    @Override
    public Set<String> keySet() throws IOException {
        return repository.findAllKeys();
    }

    @Override
    public Collection<StoredCredential> values() throws IOException {
        return repository.findAll().stream().map(c -> {
            StoredCredential credential = new StoredCredential();
            credential.setAccessToken(c.getAccessToken());
            credential.setRefreshToken(c.getRefreshToken());
            credential.setExpirationTimeMilliseconds(c.getExpirationTimeMilliseconds());
            return credential;
        }).collect(Collectors.toList());
    }

    @Override
    public StoredCredential get(String key) throws IOException {
        Optional<GoogleCredential> jpaStoredCredentialOptional = repository.findByKeyId(key);
        if (!jpaStoredCredentialOptional.isPresent()) {
            return null;
        }
        GoogleCredential googleCredential = jpaStoredCredentialOptional.get();
        StoredCredential credential = new StoredCredential();
        credential.setAccessToken(googleCredential.getAccessToken());
        credential.setRefreshToken(googleCredential.getRefreshToken());
        credential.setExpirationTimeMilliseconds(googleCredential.getExpirationTimeMilliseconds());
        return credential;
    }

    @Override
    public DataStore<StoredCredential> set(String key, StoredCredential value) throws IOException {
        GoogleCredential googleCredential = repository.findByKeyId(key).orElse(new GoogleCredential(key, value));
        googleCredential.apply(value);
        repository.save(googleCredential);
        return this;
    }

    @Override
    public DataStore<StoredCredential> clear() throws IOException {
        repository.deleteAll();
        return this;
    }

    @Override
    public DataStore<StoredCredential> delete(String key) throws IOException {
        repository.deleteById(key);
        return this;
    }
}