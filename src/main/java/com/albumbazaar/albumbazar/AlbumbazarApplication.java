package com.albumbazaar.albumbazar;

import com.albumbazaar.albumbazar.services.storage.ImageStorageProperties;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(ImageStorageProperties.class)
@EnableCaching
public class AlbumbazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumbazarApplication.class, args);
		System.out.println("Application started 💪💯✔");
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
