package com.sparta.lv3.controller;

import com.sparta.lv3.dto.LectureResponseDto;
import com.sparta.lv3.dto.TutorRequestDto;
import com.sparta.lv3.dto.TutorResponseDto;
import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.exception.AuthorityViolationException;
import com.sparta.lv3.service.TutorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public TutorResponseDto updateTutor(@PathVariable Long id,
                                        @RequestBody TutorRequestDto tutorRequestDto,
                                        HttpServletRequest request) {
        Admin admin = (Admin) request.getAttribute("admin");
        checkAuthority(admin);
        return tutorService.updateTutor(id, tutorRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutor(@PathVariable Long id, HttpServletRequest request) {
        Admin admin = (Admin) request.getAttribute("admin");
        checkAuthority(admin);
        String name = tutorService.deleteTutor(id);
        return new ResponseEntity<>(String.format("%s 강사 삭제 완료", name), HttpStatus.ACCEPTED);
    }

    private void checkAuthority(Admin admin) {
        if (admin.getDivision().getAuthority().equals("ROLE_STAFF")) {
            throw new AuthorityViolationException("수정 권한이 없습니다.");
        }
    }
}
