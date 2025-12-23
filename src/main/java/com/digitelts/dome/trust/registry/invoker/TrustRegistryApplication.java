package com.digitelts.dome.trust.registry.invoker;

import com.digitelts.dome.trust.registry.config.CustomProperties;
import com.fasterxml.jackson.databind.Module;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"com.digitelts.dome.trust.registry.invoker", "com.digitelts.dome.trust.registry.api" , "org.openapitools.configuration", "com.digitelts.dome.trust.registry.model"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@EntityScan(basePackages = {"com.digitelts.dome.trust.registry.model"})
@EnableJpaRepositories(basePackages = "com.digitelts.dome.trust.registry.repositories")
@EnableConfigurationProperties(CustomProperties.class)
public class TrustRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrustRegistryApplication.class, args);
    }

    @Bean(name = "com.digitelts.dome.trust.registry.invoker.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

    /// CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .maxAge(3600);
            }
        };
    }
}