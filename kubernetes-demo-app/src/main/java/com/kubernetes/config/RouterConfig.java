package com.kubernetes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    private final RequestHandler requestHandler;

    public RouterConfig(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }


    @Bean
    public RouterFunction<ServerResponse> higherRouterFunction() {
        return RouterFunctions.route().path("router", this::serverResponseRouterFunction).build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("user/age-between/{startAge}/{endAge}", requestHandler::userAgeHandler)
                .build();
    }

}
