package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("EBSI-like Trusted Registry API")
                                .description("This OpenAPI specification defines the REST API endpoints for interacting with the DOME EBSI-like Trusted Registry, including adapted JSON-RPC methods for blockchain transactions as REST API endpoints. ")
                                .version("4.0.1")
                )
        ;
    }
}