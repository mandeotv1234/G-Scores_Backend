package com.goldenowl.gscores.service;

import com.goldenowl.gscores.model.StudentScore;
import com.goldenowl.gscores.repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentScoreService {

    @Autowired
    private StudentScoreRepository repository;

    public StudentScore getByRegistrationNumber(String regNumber) {
        return repository.findByRegistrationNumber(regNumber)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    public Map<String, Map<String, Long>> getSubjectLevelReport() {
        List<StudentScore> scores = repository.findAll();
        Map<String, Map<String, Long>> report = new LinkedHashMap<>();

        List<String> subjects = List.of("toan", "nguVan", "ngoaiNgu", "vatLi", "hoaHoc",
                "sinhHoc", "lichSu", "diaLi", "gdcd");

        for (String subject : subjects) {
            Map<String, Long> levels = new LinkedHashMap<>();
            levels.put(">=8", 0L);
            levels.put("6-8", 0L);
            levels.put("4-6", 0L);
            levels.put("<4", 0L);

            for (StudentScore score : scores) {
                Double value = getSubjectScore(score, subject);
                if (value == null) continue;

                if (value >= 8) levels.put(">=8", levels.get(">=8") + 1);
                else if (value >= 6) levels.put("6-8", levels.get("6-8") + 1);
                else if (value >= 4) levels.put("4-6", levels.get("4-6") + 1);
                else levels.put("<4", levels.get("<4") + 1);
            }
            report.put(subject, levels);
        }

        return report;
    }

    public List<StudentScore> getTop10GroupAStudents() {
        return repository.findAll().stream()
                .filter(s -> s.getToan() != null && s.getVatLi() != null && s.getHoaHoc() != null)
                .sorted(Comparator.comparingDouble(
                        s -> -(s.getToan() + s.getVatLi() + s.getHoaHoc())
                ))
                .limit(10)
                .collect(Collectors.toList());
    }

    private Double getSubjectScore(StudentScore s, String subject) {
        return switch (subject) {
            case "toan" -> s.getToan();
            case "nguVan" -> s.getNguVan();
            case "ngoaiNgu" -> s.getNgoaiNgu();
            case "vatLi" -> s.getVatLi();
            case "hoaHoc" -> s.getHoaHoc();
            case "sinhHoc" -> s.getSinhHoc();
            case "lichSu" -> s.getLichSu();
            case "diaLi" -> s.getDiaLi();
            case "gdcd" -> s.getGdcd();
            default -> null;
        };
    }
}
