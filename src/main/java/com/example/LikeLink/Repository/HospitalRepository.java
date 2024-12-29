package com.example.LikeLink.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.LikeLink.Model.Hospital;
import java.util.Optional;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    Optional<Hospital> findByHospitalLicenseNumber(String hospitalLicenseNumber);
    boolean existsByHospitalLicenseNumber(String hospitalLicenseNumber);
    Optional<Hospital> findByEmail(String email);
}