package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LectureRequestDto {

    @NotBlank
    private String title;

    private Integer price;

    private String description;

    private Category category;

    @NotNull
    private Long tutorId;
}
