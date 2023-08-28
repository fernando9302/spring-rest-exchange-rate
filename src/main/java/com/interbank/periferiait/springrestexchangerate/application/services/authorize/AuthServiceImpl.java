package com.interbank.periferiait.springrestexchangerate.application.services.authorize;

import com.interbank.periferiait.springrestexchangerate.application.request.authorize.AuthRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.authorize.AuthResponse;
import com.interbank.periferiait.springrestexchangerate.infraestructure.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public AuthResponse login(AuthRequest authRequest) {
        final var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final var userDetails =  (UserDetails) authenticate.getPrincipal();
        return getToken(userDetails);
    }

    public AuthResponse getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        final var token = jwtService.generateToken(Map.of("role", roles), username);
        return new AuthResponse(token);
    }
}