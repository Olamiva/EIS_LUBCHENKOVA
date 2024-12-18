package com.labsfrom6to8.Config;

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
                registry.addMapping("/**") // Разрешаем все путиф
                        .allowedOrigins("http://localhost:8082") // Разрешаем доступ с фронтенда
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем методы
                        .allowedHeaders("*") // Разрешаем любые заголовки
                        .allowCredentials(true); // Если используете куки
            }
        };
    }
}

