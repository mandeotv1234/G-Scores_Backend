package com.goldenowl.gscores.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "student_scores")
public class StudentScore {
    @Id
    private String id;

    private String registrationNumber;
    private Double toan;
    private Double nguVan;
    private Double ngoaiNgu;
    private Double vatLi;
    private Double hoaHoc;
    private Double sinhHoc;
    private Double lichSu;
    private Double diaLi;
    private Double gdcd;
    private String maNgoaiNgu;

    // Getters and setters
}

