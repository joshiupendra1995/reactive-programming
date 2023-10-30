package com.kubernetes.publisher;

import com.kubernetes.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
@Slf4j
public class MessagePublisher {

    private final Sinks.Many<UserDto> sink;

    public MessagePublisher() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void publishMessage(UserDto userDto) {
        log.info("Added user{} " ,  userDto);
        this.sink.tryEmitNext(userDto);
    }

    public Flux<UserDto> getMessage() {
        return this.sink.asFlux();
    }
}
