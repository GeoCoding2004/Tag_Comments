package com.georgehabib.Tags_comments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Tells Spring that this class contains configuration code
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // apply CORS configuration at all endpoints
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")    // allow all http methods.
                        .allowedHeaders("*")    // allow any request headers
                        .allowCredentials(true);    // allows the browser to send cookies or authorization header along with the request
            }
        };
    }
}
