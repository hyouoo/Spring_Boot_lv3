package com.sparta.lv3.entity;

import com.sparta.lv3.dto.TutorRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer career;

    private String company;

    private String phone;

    @Column(length = 500)
    private String comment;

    @OneToMany(mappedBy = "tutor")
    private List<Lecture> lectureList = new ArrayList<>();

    public Tutor(TutorRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phone = requestDto.getPhone();
        this.comment = requestDto.getComment();
    }

    public Tutor updateTutor(Tutor tutor) {
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phone = tutor.getPhone();
        this.comment = tutor.getComment();
        return this;
    }
}
