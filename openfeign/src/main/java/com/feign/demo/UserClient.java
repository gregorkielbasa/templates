package com.feign.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface UserClient {

    @GetMapping("/users")
    List<userDto> getUsers();
}
