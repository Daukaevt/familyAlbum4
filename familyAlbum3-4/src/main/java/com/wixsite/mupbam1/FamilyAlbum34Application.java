package com.wixsite.mupbam1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FamilyAlbum34Application {

	public static void main(String[] args) {
		SpringApplication.run(FamilyAlbum34Application.class, args);
	}

}
