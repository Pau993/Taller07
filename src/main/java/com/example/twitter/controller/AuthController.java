package com.example.twitter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RestTemplate restTemplate;
    private final String clientId = "2vccsi2pfvafbefkdusf3rc5fn";
    private final String clientSecret = "1cqo7hlomq8m15h1o2tvj650lhp7sc0cl9edp6bc8h3dc1a8q23c";
    private final String tokenUrl = "https://us-east-2cxgzn6rk6.auth.us-east-2.amazoncognito.com/oauth2/token";
    private final String redirectUri = "http://localhost:8080/";

    public AuthController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        if (code == null || code.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Código de autorización no recibido.");
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("redirect_uri", "http://localhost:8080/auth/callback");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);

        if (!tokenResponse.getStatusCode().is2xxSuccessful()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error al obtener el token.");
            return;
        }

        try {
            String tokenJson = tokenResponse.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(tokenJson);
            String accessToken = jsonNode.get("access_token").asText();

            response.sendRedirect("http://localhost:8080?token=" + accessToken);
        } catch (JsonProcessingException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error procesando el JSON de respuesta.");
        }
    }
}

