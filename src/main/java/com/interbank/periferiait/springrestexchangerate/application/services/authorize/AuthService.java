package com.interbank.periferiait.springrestexchangerate.application.services.authorize;

import com.interbank.periferiait.springrestexchangerate.application.request.authorize.AuthRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.authorize.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequestDto);

}