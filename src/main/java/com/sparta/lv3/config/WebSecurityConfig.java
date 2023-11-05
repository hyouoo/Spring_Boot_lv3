package com.sparta.lv3.config;

import com.sparta.lv3.exception.DelegatedAuthenticationEntryPoint;
import com.sparta.lv3.filter.JwtAuthenticationFilter;
import com.sparta.lv3.filter.JwtAuthorizationFilter;
import com.sparta.lv3.filter.LoggingFilter;
import com.sparta.lv3.jwt.JwtUtil;
import com.sparta.lv3.security.AdminDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final AdminDetailsService adminDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, adminDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/api/admin/**").permitAll()
                .anyRequest().authenticated()
        );

//        http.formLogin(Customizer.withDefaults());
//        http.formLogin((formLogin) -> formLogin
//                .loginProcessingUrl("/api/login-page")
//                .permitAll());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class)
                .addFilterBefore(loggingFilter(), JwtAuthorizationFilter.class);

        http.exceptionHandling((handling) -> handling
                .authenticationEntryPoint(delegatedAuthenticationEntryPoint)
        );

        return http.build();
    }
}
