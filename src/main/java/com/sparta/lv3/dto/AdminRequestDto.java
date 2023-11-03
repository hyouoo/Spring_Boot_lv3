package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Division;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AdminRequestDto {

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()_+=])(?=\\S+$).{8,15}$")
    private String pw;

    private Division division;
}
