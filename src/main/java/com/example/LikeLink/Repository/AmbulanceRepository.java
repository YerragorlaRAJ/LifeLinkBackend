package com.example.LikeLink.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.LikeLink.Model.Ambulance;
import java.util.Optional;

@Repository
public interface AmbulanceRepository extends MongoRepository<Ambulance, String> {
    Optional<Ambulance> findByEmail(String email);
    boolean existsByEmail(String email);
}