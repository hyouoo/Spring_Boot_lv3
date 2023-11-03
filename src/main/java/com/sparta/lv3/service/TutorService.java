package com.sparta.lv3.service;

import com.sparta.lv3.dto.LectureResponseDto;
import com.sparta.lv3.dto.TutorRequestDto;
import com.sparta.lv3.dto.TutorResponseDto;
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
public class TutorService {

    private final TutorRepository tutorRepository;
    private final LectureRepository lectureRepository;

    public TutorResponseDto registerTutor(TutorRequestDto tutorRequestDto) {
        Tutor newTutor = new Tutor(tutorRequestDto);
        tutorRepository.save(newTutor);
        return new TutorResponseDto(newTutor);
    }

    public TutorResponseDto getTutor(Long id) {
        return new TutorResponseDto(findTutor(id));
    }

    public List<LectureResponseDto> getLectureByTutor(Long id) {
        if (findTutor(id) != null) {
            List<Lecture> lectureList = lectureRepository.findAllByTutorIdOrderByRegisterAtDesc(id);
            if (lectureList.isEmpty()) {
                throw new NullPointerException("강사의 등록된 강의가 없습니다,");
            }
            return lectureList.stream().map(LectureResponseDto::new).toList();
        }
        return null;
    }

    @Transactional
    public TutorResponseDto updateTutor(Long id, TutorRequestDto tutorRequestDto) {
        Tutor tutor = findTutor(id);
        tutor.updateTutor(tutorRequestDto);
        return new TutorResponseDto(tutor);
    }

    public String deleteTutor(Long id) {
        Tutor tutor = findTutor(id);
        String name = tutor.getName();
        tutorRepository.delete(tutor);
        return name;
    }

    private Tutor findTutor(Long id) {
        return tutorRepository.findById(id).orElseThrow(() ->
                new NullPointerException("요청한 강사가 없습니다."));
    }
}

