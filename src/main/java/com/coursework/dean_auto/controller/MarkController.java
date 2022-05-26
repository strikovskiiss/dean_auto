package com.coursework.dean_auto.controller;

import com.coursework.dean_auto.entity.Mark;
import com.coursework.dean_auto.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks_api")
public class MarkController {
    @Autowired
    MarkService markService;

    @PostMapping("/marks/save")
    public Mark saveMark(@RequestBody Mark mark){
        return markService.saveMark(mark);
    }

    @GetMapping("/marks/all")
    public List<Mark> getAllMarks(){
        return markService.getAllMarks();
    }
}
