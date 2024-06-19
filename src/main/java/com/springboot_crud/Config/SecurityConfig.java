package com.springboot_crud.Config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/**"
    };

    private static final String[] CHECK_AUTHORIZATION = {
            "/v1/user/logout",
    };

    public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers(WHITE_LIST_URL).permitAll()
                            .requestMatchers(CHECK_AUTHORIZATION).authenticated();
                })
                .exceptionHandling(accessDeniedHandler -> accessDeniedHandler
                        .accessDeniedHandler(customAccessDeniedHandler));

        return http.build();
    }
}
