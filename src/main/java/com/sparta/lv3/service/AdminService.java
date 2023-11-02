package com.sparta.lv3.service;

import com.sparta.lv3.dto.AdminRequestDto;
import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin signupAdmin(AdminRequestDto adminRequestDto) {
        String email = adminRequestDto.getEmail();
        String pw = passwordEncoder.encode(adminRequestDto.getPw());
        // 중복 가입 확인
        if (adminRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("중복된 Email입니다.");
        }
        // 관리자 등록
        Admin newAdmin = new Admin(email, pw, adminRequestDto.getDivision());
        return adminRepository.save(newAdmin);
    }
}
