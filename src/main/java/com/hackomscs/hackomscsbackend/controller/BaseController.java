package com.hackomscs.hackomscsbackend.controller;

import com.hackomscs.hackomscsbackend.service.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class BaseController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getSessionToken")
    public String getSessionToken(HttpSession session) {
        return (String) session.getAttribute("token");
    }

    @PostMapping("/setSessionToken")
    public String setSessionToken(HttpServletResponse session, @RequestParam String authCode) {
        String token = tokenService.getAccessToken(authCode);
        // set to HTTPOnly cookie to prevent XSS
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        session.addCookie(tokenCookie);
        return "Session token set";
    }


}
