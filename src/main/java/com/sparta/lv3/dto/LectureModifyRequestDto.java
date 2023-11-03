package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LectureModifyRequestDto {
    @NotBlank
    private String title;

    private Integer price;

    private String description;

    private Category category;
}
