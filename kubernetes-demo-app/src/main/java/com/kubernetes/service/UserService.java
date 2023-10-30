package com.kubernetes.service;

import com.kubernetes.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    public List<UserDto> AllUsers() {
        return List.of(new UserDto(1, "upendra", 1), new UserDto(2, "xyz", 5), new UserDto(3, "abc", 6));
    }

    public List<UserDto> findUsersBetweenAge(Integer startAge, Integer endAge) {
        return AllUsers().stream()
                .peek(u->log.info("user :: {}",u))
                .filter(userDto -> userDto.getAge() >= startAge && userDto.getAge() <= endAge).collect(Collectors.toList());
    }

    public Flux<UserDto> findUsersBetweenAgeFlux(Integer startAge, Integer endAge) {
        return Flux.just(new UserDto(1, "upendra", 1), new UserDto(2, "xyz", 5), new UserDto(3, "abc", 6)).map(u -> new UserDto(u.getUserId(), u.getName(), u.getAge()))
                .filter(u->u.getAge()>=startAge && u.getAge()<=endAge)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(u->log.info("user :: {}",u));
    }
}
