package com.coursework.dean_auto.repository;

import com.coursework.dean_auto.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    List<Mark> findAllByValue(Integer value);
    List<Mark> findAllBySubjectName(String subjectName);
    List<Mark> findAllByValueAndSubjectName(Integer value, String subjectName);
}
