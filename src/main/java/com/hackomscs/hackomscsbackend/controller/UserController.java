package com.hackomscs.hackomscsbackend.controller;

import com.hackomscs.hackomscsbackend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final TokenService tokenService;

    @Autowired
    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // https://my.mlh.io/docs#oauth_flows
    // step 2. receive auth code so u can trade for access token
    @GetMapping("/mymlh-callback")
    public String mlhCallBackToken(@RequestParam String authCode){
        String accessToken = tokenService.getAccessToken(authCode);
        return "Callback token: " + accessToken;

    }

    // Get user info after exchanging for access token
    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestParam String token) {
        return ResponseEntity.ok("User: " + token);
    }

    // step 1. send user to mlh login page
    // put this in frontend (its already url encoded)
    // "https://my.mlh.io/oauth/authorize?client_id=LO02M-OMjyeeqM8smtZhOgFF-IHAxtw_nzpr_4T1lHs&redirect_uri=https%3A%2F%2Fhackomscs.com%2Fmymlh-callback&response_type=token"
    // works for login and register









}
