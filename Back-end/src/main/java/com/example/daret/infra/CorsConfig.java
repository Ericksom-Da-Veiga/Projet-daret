package com.example.daret.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/membre/**")
        //         .allowedOrigins("http://localhost:3000") // Adicione o seu domínio front-end aqui
        //         .allowedMethods("GET", "POST", "PUT", "DELETE")
        //         .allowedHeaders("Content-Type");

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Adicione o seu domínio front-end aqui
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
    }
}
