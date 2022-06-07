package com.example.webserviseprojects.DTO;

import lombok.Data;
/*
*
* */
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
