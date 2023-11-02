package com.sparta.lv3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Email
    private String email;

    @NotBlank
    private String pw;
}
