package com.docs.openapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller implements com.docs.openapi.GreetingApi {
    @Override
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("hello");
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return com.docs.openapi.GreetingApi.super.getRequest();
    }
}
