package org.mymusic.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mymusic.core.dao.UsersDao;
import org.mymusic.core.domain.User;
import org.mymusic.core.domain.auth.SignUpRequest;
import org.mymusic.core.exception.ServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersDao dao;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> getByLogin(String login) {
        log.info("Getting user by login {}", login);
        return dao.byLogin(login);
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        log.info("Registering user {}", request.getLogin());
        if (dao.loginExist(request.getLogin())) {
            throw new ServiceException.LoginExistsException("Account with login " + request.getLogin() + " already exists");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setLogin(request.getLogin().trim());
        request.setName(request.getName().trim());
        dao.save(request);
    }

    public Integer getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dao.byLogin((String) principal)
                .map(User::getId)
                .orElseThrow(() -> new IllegalStateException("Could not identify current user"));
    }
}
