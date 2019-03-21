package org.mymusic.core.endpoint.api;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.User;
import org.mymusic.core.domain.auth.SignInRequest;
import org.mymusic.core.domain.auth.SignUpRequest;
import org.mymusic.core.exception.ServiceException;
import org.mymusic.core.security.JwtUtils;
import org.mymusic.core.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthEndpoint {

    private final AuthenticationManager manager;
    private final JwtUtils jwt;
    private final UsersService usersService;

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@RequestBody @Valid SignUpRequest request) {
        usersService.signUp(request);
        return singletonMap("token", jwt.generate(request.getLogin()));
    }

    @PostMapping("/sign-in")
    public Map<String, String> signIn(@RequestBody @Valid SignInRequest request) {
        Authentication authentication = manager.authenticate(getCredentials(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String login = request.getLogin();
        return usersService.getByLogin(request.getLogin())
                .map(user -> singletonMap("token", jwt.generate(login)))
                .orElseThrow(() -> new ServiceException.LoginNotFoundException(login));
    }

    @GetMapping("/me")
    public User me() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersService.getByLogin(login)
                .orElseThrow(() -> new ServiceException.LoginNotFoundException(login));
    }

    private UsernamePasswordAuthenticationToken getCredentials(SignInRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
    }
}