package com.sparta.lv3.controller;

import com.sparta.lv3.dto.AdminRequestDto;
import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupAdmin(@RequestBody @Valid AdminRequestDto adminRequestDto) {
        Admin newAdmin = adminService.signupAdmin(adminRequestDto);
        return new ResponseEntity<>(
                String.format("%s로 등록 완료", newAdmin.getDivision().getAuthority().substring(5)),
                HttpStatus.OK
        );
    }
}
