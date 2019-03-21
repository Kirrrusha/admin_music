package org.mymusic.core.domain.auth;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignInRequest {

    @Length(min = 4, max = 30)
    private String login;
    @Length(min = 6, max = 30)
    private String password;
}
