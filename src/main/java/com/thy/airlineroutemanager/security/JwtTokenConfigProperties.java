package com.thy.airlineroutemanager.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties("jwt")
@Configuration
public class JwtTokenConfigProperties {

    private String secret;
    private Integer expiration;
}
