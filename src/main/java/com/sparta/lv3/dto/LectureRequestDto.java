package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LectureRequestDto {

    @NotBlank
    private String title;

    private Integer price;

    private String description;

    @NotBlank
    private Category category;

    @NotBlank
    private Long tutorId;
}
