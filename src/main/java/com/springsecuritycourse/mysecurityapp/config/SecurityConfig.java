package com.springsecuritycourse.mysecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .anyRequest().authenticated()
                );

        http.formLogin(configurer ->
                configurer.disable());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // InMemoryUserDetailsManager
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("icad").password("{noop}12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{noop}12345").authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
