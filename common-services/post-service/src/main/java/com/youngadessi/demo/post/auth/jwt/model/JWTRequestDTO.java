package com.youngadessi.demo.post.auth.jwt.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JWTRequestDTO implements Serializable {

    private String username;

    private String password;

}
