package com.institute.controller;

import com.institute.repository.FacultyRepository;
import com.institute.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/birthdays")
@CrossOrigin(origins = "http://localhost:3000")
public class BirthdayController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private FacultyRepository facultyRepo;

    @GetMapping("/today")
    public Map<String, Object> todayBirthdays() {

        Map<String, Object> res = new HashMap<>();
        res.put("students", studentRepo.findTodayBirthdays());
        res.put("faculty", facultyRepo.findTodayBirthdays());

        return res;
    }
}

