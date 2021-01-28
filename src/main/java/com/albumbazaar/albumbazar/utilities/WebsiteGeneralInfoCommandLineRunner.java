package com.albumbazaar.albumbazar.utilities;

import java.util.Optional;

import com.albumbazaar.albumbazar.dao.WebsiteGeneralInfoRepository;
import com.albumbazaar.albumbazar.model.WebsiteGeneralInfoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebsiteGeneralInfoCommandLineRunner implements CommandLineRunner {

    private final WebsiteGeneralInfoRepository websiteGeneralInfoRepository;

    @Autowired
    public WebsiteGeneralInfoCommandLineRunner(final WebsiteGeneralInfoRepository websiteGeneralInfoRepository) {
        this.websiteGeneralInfoRepository = websiteGeneralInfoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Optional<WebsiteGeneralInfoEntity> websiteInfoEntity = websiteGeneralInfoRepository.findById(1l);

        final boolean isPresent = websiteInfoEntity.isPresent();

        if (!isPresent) {
            final WebsiteGeneralInfoEntity websiteGeneralInfoEntity = new WebsiteGeneralInfoEntity();
            websiteGeneralInfoEntity.setId(1l);
            System.out.println(websiteGeneralInfoEntity);
            websiteGeneralInfoRepository.save(websiteGeneralInfoEntity);
        }

    }

}
