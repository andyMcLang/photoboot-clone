package com.andylang.photoz_clone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Salli kaikki endpointit
                        .allowedOrigins("http://localhost:63342") // Frontendin osoite
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Sallitut HTTP-metodit
            }
        };
    }
}
