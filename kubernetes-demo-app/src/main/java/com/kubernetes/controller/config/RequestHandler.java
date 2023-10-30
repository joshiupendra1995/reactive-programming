package com.kubernetes.controller.config;

import com.kubernetes.model.UserDto;
import com.kubernetes.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Component
public class RequestHandler {

    private final UserService userService;

    public RequestHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> userAgeHandler(ServerRequest serverRequest){
        Optional<String> startAgeOpt = serverRequest.queryParam("startAge");
        Optional<String> endAgeOpt = serverRequest.queryParam("endAge");
        if(startAgeOpt.isPresent() && endAgeOpt.isPresent()){
            return ServerResponse.ok().body(userService.findUsersBetweenAgeFlux(Integer.parseInt(startAgeOpt.get()),Integer.parseInt(endAgeOpt.get())),UserDto.class);
        }
        return ServerResponse.badRequest().bodyValue(new RuntimeException("No User found"));
    }
}
