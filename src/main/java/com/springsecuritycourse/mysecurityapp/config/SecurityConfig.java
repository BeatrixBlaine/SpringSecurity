package com.springsecuritycourse.mysecurityapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .anyRequest().authenticated()
                );

        http.formLogin(Customizer.withDefaults());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // InMemoryUserDetailsManager
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("icad").password("{noop}12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$SXPB4y./VObqKoytywYi8OU4ozTT/Z3hmOCdcJB9.3Ztj9jbxlDzq").authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Checks if password have been compromised, and suggest user to change the password
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
