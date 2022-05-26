package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Subject;
import com.coursework.dean_auto.exception.EntityException;
import com.coursework.dean_auto.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        if (subjects.isEmpty()){
            throw new EntityException("There are no subjects in this database!");
        }
        return  subjects;
    }

}
