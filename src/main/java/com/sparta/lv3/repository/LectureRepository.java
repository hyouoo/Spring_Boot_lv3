package com.sparta.lv3.repository;

import com.sparta.lv3.entity.Category;
import com.sparta.lv3.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAllByTutorIdOrderByRegisterAtDesc(Long id);

    List<Lecture> findAllByCategoryOrderByRegisterAtDesc(Category category);
}
