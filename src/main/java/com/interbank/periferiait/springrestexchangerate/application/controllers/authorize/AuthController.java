package com.interbank.periferiait.springrestexchangerate.application.controllers.authorize;

import com.interbank.periferiait.springrestexchangerate.application.request.authorize.AuthRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.authorize.AuthResponse;
import com.interbank.periferiait.springrestexchangerate.application.services.authorize.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/authorize")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Authorize", description = "Login with username and password")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authRequest(@RequestBody AuthRequest authRequest) {
        var userRegistrationResponse = authService.login(authRequest);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);
    }


}