package com.hackomscs.hackomscsbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    private final RestTemplate restTemplate = new RestTemplate();

    // GCloud env variables
    private String clientId = System.getenv("MYMLH_CLIENT_ID");
    private String clientSecret = System.getenv("MYMLH_CLIENT_SECRET");
    private String redirectUri = System.getenv("MYMLH_REDIRECT_URI");

    public String getAccessToken(String authCode) {
        return restTemplate.postForObject(
                String.format(
                        "https://my.mlh.io/oauth/token?client_id=%s&client_secret=%s&code=%s&grant_type=authorization_code&redirect_uri=%s",
                        clientId, clientSecret, authCode, redirectUri
                ),
                null,
                String.class
        );
    }
}
