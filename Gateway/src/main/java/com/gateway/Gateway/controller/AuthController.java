package com.gateway.Gateway.controller;


import com.gateway.Gateway.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> logIn(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model
    )
    {
        ApiResponse response = new ApiResponse();
        response.setUserId(user.getEmail());
        response.setAccessToken(client.getAccessToken().getTokenValue());
        response.setRefreshToken(client.getRefreshToken().getTokenValue());
        response.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        response.setAuthorities(authorities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
