package com.sparta.lv3.controller;

import com.sparta.lv3.dto.LectureModifyRequestDto;
import com.sparta.lv3.dto.LectureRequestDto;
import com.sparta.lv3.dto.LectureResponseDto;
import com.sparta.lv3.entity.Category;
import com.sparta.lv3.entity.Division;
import com.sparta.lv3.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/register")
    public LectureResponseDto registerLecture(@RequestBody @Valid LectureRequestDto requestDto) {
        return lectureService.registerLecture(requestDto);
    }

    @GetMapping("/{id}")
    public LectureResponseDto getLecture(@PathVariable Long id) {
        return lectureService.getLecture(id);
    }

    @GetMapping("/query")
    public List<LectureResponseDto> getLectureByCategory(@RequestParam String cat) {
        Category category = Category.valueOf(cat);
        return lectureService.getLectureByCategory(category);
    }

    @Secured(Division.Authority.MANAGER)
    @PutMapping("/{id}")
    public LectureResponseDto updateLecture(@PathVariable Long id,
                                            @RequestBody @Valid LectureModifyRequestDto requestDto) {
        return lectureService.updateLecture(id, requestDto);
    }

    @Secured(Division.Authority.MANAGER)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLecture(@PathVariable Long id) {
        String title = lectureService.deleteLecture(id);
        return new ResponseEntity<>(String.format("%s 강의 삭제 완료", title), HttpStatus.ACCEPTED);
    }
}
