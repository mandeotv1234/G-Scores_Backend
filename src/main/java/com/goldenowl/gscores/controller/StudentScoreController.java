package com.goldenowl.gscores.controller;

import com.goldenowl.gscores.model.StudentScore;
import com.goldenowl.gscores.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*") // Optional: Allow frontend access
public class StudentScoreController {

    @Autowired
    private StudentScoreService studentScoreService;

    // 1. Get score by registration number
    @GetMapping("/{regNumber}")
    public StudentScore getScoreByRegNumber(@PathVariable String regNumber) {
        return studentScoreService.getByRegistrationNumber(regNumber);
    }

    // 2. Get subject-level report (4 score ranges)
    @GetMapping("/report/subject-distribution")
    public Map<String, Map<String, Long>> getSubjectReport() {
        return studentScoreService.getSubjectLevelReport();
    }

    // 3. Get top 10 students in Group A (Math + Physics + Chemistry)
    @GetMapping("/top10/groupA")
    public List<StudentScore> getTop10GroupAStudents() {
        return studentScoreService.getTop10GroupAStudents();
    }
}
