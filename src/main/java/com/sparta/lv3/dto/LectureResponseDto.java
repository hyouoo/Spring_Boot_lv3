package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Category;
import com.sparta.lv3.entity.Lecture;
import lombok.Getter;

@Getter
public class LectureResponseDto {
    private final String title;

    private final Integer price;

    private final String description;

    private final Category category;

    public LectureResponseDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
    }
}
