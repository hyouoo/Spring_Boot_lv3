package com.sparta.lv3.entity;

import com.sparta.lv3.dto.LectureModifyRequestDto;
import com.sparta.lv3.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Lecture extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    @Column(length = 500)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorId", nullable = false)
    private Tutor tutor;

    public Lecture(LectureRequestDto requestDto, Tutor tutor) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.tutor = tutor;
    }

    public void updateLecture(LectureModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.description = requestDto.getDescription();
    }
}
