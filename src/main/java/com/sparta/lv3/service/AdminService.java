package com.sparta.lv3.service;

import com.sparta.lv3.dto.AdminRequestDto;
import com.sparta.lv3.dto.LoginRequestDto;
import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.jwt.JwtUtil;
import com.sparta.lv3.repository.AdminRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Admin signupAdmin(AdminRequestDto adminRequestDto) {
        String email = adminRequestDto.getEmail();
        String pw = passwordEncoder.encode(adminRequestDto.getPw());
        // Duplication check
        if (adminRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("중복된 Email");
        }
        // register Admin
        Admin newAdmin = new Admin(email, pw, adminRequestDto.getDivision());
        return adminRepository.save(newAdmin);
    }

    public Admin loginAdmin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String pw = loginRequestDto.getPw();
        // Email check
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new NullPointerException("등록된 관리자가 아님"));
        // Password check
        if (!passwordEncoder.matches(pw, admin.getPw())) {
            throw new IllegalArgumentException("비밀번호 불일치!");
        }
        // create JWT, add to cookie
        String token = jwtUtil.createToken(admin.getEmail(), admin.getDivision());
        jwtUtil.addJwtToCookie(token, response);
        return admin;
    }
}
