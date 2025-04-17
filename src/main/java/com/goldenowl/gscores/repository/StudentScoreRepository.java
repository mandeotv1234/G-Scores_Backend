package com.goldenowl.gscores.repository;

import com.goldenowl.gscores.model.StudentScore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentScoreRepository extends MongoRepository<StudentScore, String> {
    Optional<StudentScore> findByRegistrationNumber(String registrationNumber);
}

