package org.mymusic.core.dao;

import lombok.RequiredArgsConstructor;
import org.mymusic.core.domain.User;
import org.mymusic.core.domain.auth.SignUpRequest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class UsersDao {

    private final NamedParameterJdbcTemplate jdbc;

    public Optional<User> byLogin(String login) {
        String query = "SELECT * FROM users u " +
                "WHERE u.login = :login AND active = TRUE";
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(query, singletonMap("login", login), new BeanPropertyRowMapper<>(User.class))
            );
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public void save(SignUpRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", request.getName());
        parameters.put("password_hash", request.getPassword());
        parameters.put("login", request.getLogin());
        new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("users")
                .usingColumns(parameters.keySet().toArray(new String[0]))
                .execute(parameters);
    }

    public Boolean loginExist(String login) {
        return jdbc.queryForObject("SELECT count(1) > 0 from users WHERE login = :login",
                singletonMap("login", login), Boolean.class);
    }
}
