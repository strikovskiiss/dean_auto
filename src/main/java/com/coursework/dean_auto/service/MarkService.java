package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Mark;

import java.util.List;

public interface MarkService {
    Mark saveMark(Mark mark);
    List<Mark> getAllMarks();
}
