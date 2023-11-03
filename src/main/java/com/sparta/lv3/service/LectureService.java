package com.sparta.lv3.service;

import com.sparta.lv3.dto.LectureModifyRequestDto;
import com.sparta.lv3.dto.LectureRequestDto;
import com.sparta.lv3.dto.LectureResponseDto;
import com.sparta.lv3.entity.Category;
import com.sparta.lv3.entity.Lecture;
import com.sparta.lv3.entity.Tutor;
import com.sparta.lv3.repository.LectureRepository;
import com.sparta.lv3.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow(() ->
                new NullPointerException("등록된 강사 정보가 없습니다."));
        Lecture newLecture = new Lecture(requestDto, tutor);
        lectureRepository.save(newLecture);
        return new LectureResponseDto(newLecture);
    }

    public LectureResponseDto getLecture(Long id) {
        return new LectureResponseDto(findLecture(id));
    }

    public List<LectureResponseDto> getLectureByCategory(Category category) {
        List<Lecture> lectureList = lectureRepository.findAllByCategoryOrderByRegisterAtDesc(category);
        if (lectureList.isEmpty()) {
            throw new NullPointerException(String.format("%s 분야의 강의가 없습니다.", category));
        }
        return lectureList.stream().map(LectureResponseDto::new).toList();
    }

    @Transactional
    public LectureResponseDto updateLecture(Long id, LectureModifyRequestDto requestDto) {
        Lecture lecture = findLecture(id);
        lecture.updateLecture(requestDto);
        return new LectureResponseDto(lecture);
    }

    public String deleteLecture(Long id) {
        Lecture lecture = findLecture(id);
        String title = lecture.getTitle();
        lectureRepository.delete(lecture);
        return title;
    }

    private Lecture findLecture(Long id) {
        return lectureRepository.findById(id).orElseThrow(() ->
                new NullPointerException("강의 정보가 없습니다."));
    }
}
