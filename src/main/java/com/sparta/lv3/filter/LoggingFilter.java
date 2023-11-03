package com.sparta.lv3.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Slf4j(topic = "LoggingFilter")
//@Component
@Order(1)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        log.info(url);

        chain.doFilter(request, response);

        log.info("비즈니스 로직 완료");
    }
}
