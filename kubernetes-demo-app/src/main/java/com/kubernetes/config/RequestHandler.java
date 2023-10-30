package com.kubernetes.config;

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
        String startAgeOpt = serverRequest.pathVariable("startAge");
        String endAgeOpt = serverRequest.pathVariable("endAge");
        if(!startAgeOpt.isEmpty() && !endAgeOpt.isEmpty()){
            return ServerResponse.ok().body(userService.findUsersBetweenAgeFlux(Integer.parseInt(startAgeOpt),Integer.parseInt(endAgeOpt)),UserDto.class);
        }
        return ServerResponse.badRequest().bodyValue(new RuntimeException("No User found"));
    }
}
