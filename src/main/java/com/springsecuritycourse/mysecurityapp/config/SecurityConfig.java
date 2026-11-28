package com.springsecuritycourse.mysecurityapp.config;

import com.springsecuritycourse.mysecurityapp.exception.CustomAccessDeniedHandler;
import com.springsecuritycourse.mysecurityapp.exception.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.sessionManagement(configurer ->
                configurer
                        .invalidSessionUrl("/invalidSession")
                );

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/register","/invalidSession","/login/**","/").permitAll()
                        .anyRequest().authenticated()
                );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(configurer ->
                configurer
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );
        http.exceptionHandling(configurer ->
                configurer
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));
        http.csrf(csrf -> csrf.disable());

        return http.build();
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

    // InMemoryUserDetailsManager
    /* @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("icad").password("{noop}12345").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$SXPB4y./VObqKoytywYi8OU4ozTT/Z3hmOCdcJB9.3Ztj9jbxlDzq").authorities("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    } */

    // JdbcUsersDetailsManager
    /* @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    } */
}
