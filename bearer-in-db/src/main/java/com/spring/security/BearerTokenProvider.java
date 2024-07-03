package com.spring.security;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BearerTokenProvider {

    private static final Map<String, String> tokenStore = new HashMap<>();

    public static String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, username);
        return token;
    }

    public static boolean validateToken(String token) {
        return tokenStore.containsKey(token);
    }

    public static String getUserNameFromToken(String token) {
        return tokenStore.get(token);
    }
}
