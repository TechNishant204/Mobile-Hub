package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.examly.springapp.model.ConstantVariableClass;

@Configuration
public class CrosConfig {

    @Bean
    public CorsFilter addCorsMappings() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin(ConstantVariableClass.FRONTEND_URL);
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter(source); 
     }
}
