package com.project.taskservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final String MANAGER_ROLE = "MANAGER";
    private final String PATH_TO_TASK_CONTROLLER = "/api/v1/task";
    private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        
        http.authorizeHttpRequests().
            requestMatchers(HttpMethod.POST, PATH_TO_TASK_CONTROLLER).
            hasRole(MANAGER_ROLE).
            requestMatchers(HttpMethod.DELETE, PATH_TO_TASK_CONTROLLER).
            hasRole(MANAGER_ROLE).
            requestMatchers(HttpMethod.PUT, PATH_TO_TASK_CONTROLLER).
            hasRole(MANAGER_ROLE).
            anyRequest().authenticated();
        
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtConverter);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
        return http.build();
    }

}
