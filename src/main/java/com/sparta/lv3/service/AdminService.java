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
        String password = passwordEncoder.encode(adminRequestDto.getPassword());
        // Duplication check
        if (adminRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("중복된 Email");
        }
        // register Admin
        Admin newAdmin = new Admin(email, password, adminRequestDto.getDivision());
        return adminRepository.save(newAdmin);
    }
}
