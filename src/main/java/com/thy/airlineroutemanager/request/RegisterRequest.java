package com.thy.airlineroutemanager.request;

import com.thy.airlineroutemanager.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;
}
