package com.sparta.lv3.security;

import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public AdminDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("등록된 관리자가 아닙니다."));
        return new AdminDetails(admin);
    }
}
