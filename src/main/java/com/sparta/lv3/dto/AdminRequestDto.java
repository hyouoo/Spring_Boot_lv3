package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Division;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminRequestDto {

    @Email
    private String email;

    @NotBlank
    private String pw;

    @NotBlank
    private Division division;
}
