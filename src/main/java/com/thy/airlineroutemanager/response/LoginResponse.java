package com.thy.airlineroutemanager.response;

import com.thy.airlineroutemanager.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    
    private final String token;
    private final Role role;
}
