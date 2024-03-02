package com.kubernetes;

import com.kubernetes.model.UserDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class GetRequestParamTest extends BaseTest{

    @Test
    public void stepVerifierRequestParamTest(){
        Flux<UserDto> userDto = webClient
                .get()
                .uri(u->u.path("/user/age-between-flux").query("startAge={startAge}&endAge={endAge}").build(1,10))
                .retrieve()
                .bodyToFlux(UserDto.class);

        StepVerifier.create(userDto)
                .expectNextMatches(r->r.getAge().equals(1))
                .expectNextMatches(r->r.getAge().equals(5))
                .expectNextMatches(r->r.getAge().equals(6))
                .verifyComplete();

    }
}
