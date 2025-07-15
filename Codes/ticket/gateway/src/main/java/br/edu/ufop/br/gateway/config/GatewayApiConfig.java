package br.edu.ufop.br.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GatewayApiConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", p -> p.path("/users")
                    .uri("lb://user-service"))
                .route("users-segment", p -> p.path("/users/**")
                .uri("lb://user-service")).
                route("sales", p -> p.path("/sales/**")
                    .uri("lb://sales-service")).
                build();

    }

}
