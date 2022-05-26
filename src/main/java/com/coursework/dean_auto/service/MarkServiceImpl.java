package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Mark;
import com.coursework.dean_auto.exception.EntityException;
import com.coursework.dean_auto.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService{
    @Autowired
    MarkRepository marksRepository;

    @Override
    public Mark saveMark(Mark mark) {
        return marksRepository.save(mark);
    }

    @Override
    public List<Mark> getAllMarks(){
        List<Mark> marks = marksRepository.findAll();
        if (marks.isEmpty()){
            throw new EntityException("There are no marks in this database!");
        }
        return marks;
    }
}
