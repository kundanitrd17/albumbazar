package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.SampleAlbumEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleAlbumRespository extends JpaRepository<SampleAlbumEntity, Long> {
    
}
