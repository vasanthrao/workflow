package com.metaverse.workflow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            try {
                csrf.disable().authorizeHttpRequests(authroize -> authroize.requestMatchers("**").permitAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }

}
