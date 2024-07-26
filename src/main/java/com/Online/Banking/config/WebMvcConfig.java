package com.Online.Banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS on all endpoints
                .allowedOrigins("*") // Allow this origin (you can specify multiple origins)
                .allowedMethods("*") // Allow these HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }


}
