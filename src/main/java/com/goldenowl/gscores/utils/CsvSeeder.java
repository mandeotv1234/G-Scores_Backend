package com.goldenowl.gscores.utils;

import com.goldenowl.gscores.model.StudentScore;
import com.goldenowl.gscores.repository.StudentScoreRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class CsvSeeder implements CommandLineRunner {

    @Autowired
    private StudentScoreRepository repository;

    @Override
    public void run(String... args) throws Exception {
        InputStream is = new ClassPathResource("diem_thi_thpt_2024.csv").getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        for (CSVRecord record : parser) {
            StudentScore score = new StudentScore();
            score.setRegistrationNumber(record.get("sbd"));
            score.setToan(parseDouble(record.get("toan")));
            score.setNguVan(parseDouble(record.get("ngu_van")));
            score.setNgoaiNgu(parseDouble(record.get("ngoai_ngu")));
            score.setVatLi(parseDouble(record.get("vat_li")));
            score.setHoaHoc(parseDouble(record.get("hoa_hoc")));
            score.setSinhHoc(parseDouble(record.get("sinh_hoc")));
            score.setLichSu(parseDouble(record.get("lich_su")));
            score.setDiaLi(parseDouble(record.get("dia_li")));
            score.setGdcd(parseDouble(record.get("gdcd")));
            score.setMaNgoaiNgu(record.get("ma_ngoai_ngu"));

            repository.save(score);
        }
    }

    private Double parseDouble(String val) {
        try {
            return val == null || val.isEmpty() ? null : Double.parseDouble(val);
        } catch (Exception e) {
            return null;
        }
    }
}
