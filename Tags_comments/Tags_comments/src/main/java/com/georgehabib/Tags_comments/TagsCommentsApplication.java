package com.georgehabib.Tags_comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class TagsCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagsCommentsApplication.class, args);
	}

}
