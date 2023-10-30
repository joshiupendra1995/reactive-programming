package com.kubernetes.controller;

import com.kubernetes.model.UserDto;
import com.kubernetes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.List;

@RestController
@RequestMapping("/user")
public class WebFluxController {

    @Autowired
    private UserService userService;

    @GetMapping("/age-between")
    public List<UserDto> findUserBetweenAge(@RequestParam Integer startAge, @RequestParam Integer endAge) {
        return userService.findUsersBetweenAge(startAge, endAge);
    }

    @GetMapping(value = "/age-between-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDto> findUserBetweenAgeFlux(@RequestParam Integer startAge, @RequestParam Integer endAge) {
        return userService.findUsersBetweenAgeFlux(startAge, endAge);
    }
}
