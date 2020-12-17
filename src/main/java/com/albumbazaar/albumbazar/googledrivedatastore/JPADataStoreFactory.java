package com.albumbazaar.albumbazar.googledrivedatastore;

import java.io.IOException;
import java.io.Serializable;

import com.albumbazaar.albumbazar.dao.GoogleCredentialRepository;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class JPADataStoreFactory implements DataStoreFactory {

    private final GoogleCredentialRepository repository;

    @Autowired(required = true)
    public JPADataStoreFactory(GoogleCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {

        return (DataStore<V>) new JPADataStore(this, id, repository);
    }

}
