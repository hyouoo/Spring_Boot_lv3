package com.sparta.lv3.filter;

import com.sparta.lv3.entity.Admin;
import com.sparta.lv3.jwt.JwtUtil;
import com.sparta.lv3.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
//@Component
//@Order(2)
@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) && url.startsWith("/api/admin")) {
            chain.doFilter(request, response);
        } else {
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);
            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Not Valid");
                }
                Claims info = jwtUtil.getAdminInfoFromToken(token);

                Admin admin = adminRepository.findByEmail(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("등록되지 않은 관리자"));
                request.setAttribute("admin", admin);
                chain.doFilter(request, response);
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}
