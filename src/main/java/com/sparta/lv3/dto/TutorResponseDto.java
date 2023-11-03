package com.sparta.lv3.dto;

import com.sparta.lv3.entity.Tutor;
import lombok.Getter;

@Getter
public class TutorResponseDto {
    private final String name;
    private final Integer career;
    private final String company;
    private final String phone;
    private final String comment;

    public TutorResponseDto(Tutor tutor) {
        this.name = tutor.getName();
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phone = tutor.getPhone();
        this.comment = tutor.getComment();
    }
}
