package com.kubernetes.controller;

import com.kubernetes.model.UserDto;
import com.kubernetes.publisher.MessagePublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/stream")
public class MessageController {

    private final MessagePublisher messagePublisher;

    AtomicInteger integer = new AtomicInteger(1);

    public MessageController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping("/publish")
    public void publishMessage() {
        UserDto userDto = new UserDto(integer.getAndIncrement(),"uj"+integer.get(), integer.get()+1);
        messagePublisher.publishMessage(userDto);
    }

    @GetMapping("/messages")
    public Flux<UserDto> getUsers() {
        return messagePublisher.getMessage();
    }

}
