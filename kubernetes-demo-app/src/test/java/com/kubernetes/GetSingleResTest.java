package com.kubernetes;

import com.kubernetes.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class GetSingleResTest extends BaseTest{

    @Test
    public void blockTest(){
        UserDto userDto = webClient
                .get()
                .uri("router/user/age-between/{startAge}/{endAge}",1,10)
                .retrieve()
                .bodyToFlux(UserDto.class)
                .blockFirst();

        log.info("{}=>",userDto);

    }

    @Test
    public void stepVerifierTest(){
        Flux<UserDto> userDto = webClient
                .get()
                .uri("router/user/age-between/{startAge}/{endAge}",1,10)
                .retrieve()
                .bodyToFlux(UserDto.class);

        StepVerifier.create(userDto)
                .expectNextMatches(r->r.getAge().equals(1))
                .expectNextMatches(r->r.getAge().equals(5))
                .expectNextMatches(r->r.getAge().equals(6))
                .verifyComplete();

    }
}
