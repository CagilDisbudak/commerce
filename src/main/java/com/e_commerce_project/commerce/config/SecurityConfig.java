package com.e_commerce_project.commerce.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails superAdmin = User
                .builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(superAdmin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/dashboard").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"api/v1/person/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/person/index","api/v1/person/register","api/v1/person/getPerson").permitAll().anyRequest().authenticated()
/*
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll().anyRequest().authenticated()
*/
        );

        httpSecurity // Login Page
                .formLogin(form -> form
                        .defaultSuccessUrl("/index",true)
                );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
