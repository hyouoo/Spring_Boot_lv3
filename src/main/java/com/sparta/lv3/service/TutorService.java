package com.sparta.lv3.service;

import com.sparta.lv3.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

}

