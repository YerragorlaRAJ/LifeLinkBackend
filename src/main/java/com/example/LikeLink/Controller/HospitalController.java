package com.example.LikeLink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.LikeLink.Service.HospitalService;
import com.example.LikeLink.dto.request.HospitalRequest;
import com.example.LikeLink.dto.response.HospitalResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hospital")
@PreAuthorize("hasRole('HOSPITAL')")
@RequiredArgsConstructor
@Validated
@Slf4j
public class HospitalController {

    @Autowired
    private final HospitalService hospitalService;

    @PostMapping("/profile")
    public ResponseEntity<HospitalResponse> createProfile(
            @Valid @RequestBody HospitalRequest hospitalRequest) {
        log.info("Creating profile for hospital license number: {}", hospitalRequest.getHospitalLicenseNumber());
        HospitalResponse savedHospital = hospitalService.createProfile(hospitalRequest);
        return ResponseEntity.ok(savedHospital);
    }

    @GetMapping("/profile/{licenseNumber}")
    public ResponseEntity<HospitalResponse> getProfile(@PathVariable String licenseNumber) {
        log.info("Fetching profile for hospital license number: {}", licenseNumber);
        HospitalResponse hospital = hospitalService.getProfile(licenseNumber);
        return ResponseEntity.ok(hospital);
    }

    @PutMapping("/profile")
    public ResponseEntity<HospitalResponse> updateProfile(
            @Valid @RequestBody HospitalRequest hospitalRequest) {
        log.info("Updating profile for hospital license number: {}", hospitalRequest.getHospitalLicenseNumber());
        HospitalResponse updatedHospital = hospitalService.updateProfile(hospitalRequest);
        return ResponseEntity.ok(updatedHospital);
    }
}