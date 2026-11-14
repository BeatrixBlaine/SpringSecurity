package com.springsecuritycourse.mysecurityapp.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


// Custom Authentication Provider for profile "prod"
@Component
@Profile("prod")
public class CustomProdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomProdAuthenticationProvider(UserDetailsService userDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // modify how the username and password is authenticated
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // extract the username from authentication object
        String username = authentication.getName();

        // extract the password from authentication object
        String pwd = authentication.getCredentials().toString();

        // create a UserDetails object from custom UserDetailsService that look for username in our custom table
        // to retrieve the password from database
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // password authentication never happens, it makes user can insert any password
        return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
