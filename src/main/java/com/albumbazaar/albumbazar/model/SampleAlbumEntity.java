package com.albumbazaar.albumbazar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.albumbazaar.albumbazar.controller.FileUploadController;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import lombok.Data;

@Entity
@Table(name = "sample_album")
@Data
public class SampleAlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    private String description;

    public String getImage() {
        if (image == null || image.isBlank())
            return null;

        return MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", image).build().toUri()
                .toString();
    }

}
