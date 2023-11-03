package com.sparta.lv3.controller;

import com.sparta.lv3.dto.LectureResponseDto;
import com.sparta.lv3.dto.TutorRequestDto;
import com.sparta.lv3.dto.TutorResponseDto;
import com.sparta.lv3.entity.Division;
import com.sparta.lv3.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutor")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/register")
    public TutorResponseDto registerTutor(@RequestBody @Valid TutorRequestDto tutorRequestDto) {
        return tutorService.registerTutor(tutorRequestDto);
    }

    @GetMapping("/{id}")
    public TutorResponseDto getTutor(@PathVariable Long id) {
        return tutorService.getTutor(id);
    }

    @GetMapping("/{id}/lecture")
    public List<LectureResponseDto> getLectureByTutor(@PathVariable Long id) {
        return tutorService.getLectureByTutor(id);
    }

    @Secured(Division.Authority.MANAGER)
    @PutMapping("/{id}")
    public TutorResponseDto updateTutor(@PathVariable Long id, @RequestBody TutorRequestDto tutorRequestDto) {
        return tutorService.updateTutor(id, tutorRequestDto);
    }

    @Secured(Division.Authority.MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutor(@PathVariable Long id) {
        String name = tutorService.deleteTutor(id);
        return new ResponseEntity<>(String.format("%s 강사 삭제 완료", name), HttpStatus.ACCEPTED);
    }
}
