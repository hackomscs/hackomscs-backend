package com.hackomscs.hackomscsbackend.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {


    private final RestTemplate restTemplate = new RestTemplate();

    // TODO: put secret in env variables
    // rn it is in application.properties

    @Value("${mymlh.client.id}")
    private String clientId;

    @Value("${mymlh.client.secret}")
    private String clientSecret;

    @Value("${mymlh.redirect.uri}")
    private String redirectUri;

    // step 3 in https://my.mlh.io/docs#oauth_flows
    public String getAccessToken(String authCode) {
        final String MYMLH_TOKEN_URL = "https://my.mlh.io/oauth/token";

        String url = MYMLH_TOKEN_URL + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + authCode + "&grant_type=authorization_code&redirect_uri=" + redirectUri;

        return restTemplate.postForObject(url, null, String.class);

    }
}
