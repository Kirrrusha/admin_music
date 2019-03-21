package org.mymusic.core.security;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.User;
import org.mymusic.core.service.UsersService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class DaoAuthenticationProvider implements AuthenticationProvider {

    private final UsersService service;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(),
                password = authentication.getCredentials().toString();
        User user = service.getByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        if (encoder.matches(password, user.getPasswordHash())) {
            return new UsernamePasswordAuthenticationToken(username,null, emptyList());
        } else {
            throw new BadCredentialsException("Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
