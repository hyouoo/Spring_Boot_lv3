package com.sparta.lv3.entity;

import com.sparta.lv3.dto.AdminRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "admin")
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pw;

    @Enumerated(value = EnumType.STRING)
    private Division division;

    public Admin(String email, String pw, Division division) {
        this.email = email;
        this.pw = pw;
        this.division = division;
    }
}
