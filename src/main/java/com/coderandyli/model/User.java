package com.coderandyli.model;

import lombok.Data;

import java.security.Principal;

/**
 * 用户实体类
 *
 * @author mydlq
 */
@Data
public class User implements Principal {
    private String username;
    private String password;
    private String name;
    private String sex;
}
