package com.sparta.lv3.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TutorRequestDto {

    @NotBlank
    private String name;

    private Integer career;

    private String company;

    private String phone;

    private String comment;
}
