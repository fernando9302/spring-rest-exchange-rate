package com.interbank.periferiait.springrestexchangerate.infraestructure.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String userName;
    private String password;
    private List<String> roles;
}
