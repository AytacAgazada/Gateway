//package com.example.apigateway;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Bütün yollar üçün CORS-u aktiv edin
//                        .allowedOrigins("http://localhost:3000") // Sizin React frontend-inizin URL-i
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // İcazə verilən HTTP metodları
//                        .allowedHeaders("*") // Bütün başlıqlara icazə verin
//                        .allowCredentials(true); // Əgər cookie və ya avtorizasiya başlıqları göndərirsinizsə
//            }
//        };
//    }
//}