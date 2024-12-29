package com.example.LikeLink.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LikeLink.Exception.ResourceNotFoundException;
import com.example.LikeLink.Model.Hospital;
import com.example.LikeLink.Repository.HospitalRepository;
import com.example.LikeLink.Repository.UserRepository;
import com.example.LikeLink.dto.request.HospitalRequest;
import com.example.LikeLink.dto.response.HospitalResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalService {
    @Autowired
    private final HospitalRepository hospitalRepository; 
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public HospitalResponse createProfile(HospitalRequest request) {
        log.debug("Creating hospital profile for license number: {}", request.getHospitalLicenseNumber());

        userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        if (hospitalRepository.existsByHospitalLicenseNumber(request.getHospitalLicenseNumber())) {
            throw new IllegalStateException("Profile already exists for hospital with license number: " + request.getHospitalLicenseNumber());
        }

        Hospital hospital = Hospital.builder()
            .hospitalLicenseNumber(request.getHospitalLicenseNumber())
            .email(request.getEmail())
            .hospitalName(request.getHospitalName())
            .isAccredited(request.getIsAccredited())
            .specialtiesOffered(request.getSpecialtiesOffered())
            .yearsOfExperienceInHealthcare(request.getYearsOfExperienceInHealthcare())
            .specializedCareUnits(request.getSpecializedCareUnits())
            .createdAt(LocalDateTime.now())
            .lastUpdatedAt(LocalDateTime.now())
            .build();

        Hospital savedHospital = hospitalRepository.save(hospital);
        log.info("Created hospital profile for license number: {}", request.getHospitalLicenseNumber());
        
        return mapToHospitalResponse(savedHospital);
    }

    public HospitalResponse getProfile(String hospitalLicenseNumber) {
        log.debug("Fetching hospital profile for license number: {}", hospitalLicenseNumber);
        
        Hospital hospital = hospitalRepository.findByHospitalLicenseNumber(hospitalLicenseNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Hospital profile not found for license number: " + hospitalLicenseNumber));
            
        return mapToHospitalResponse(hospital);
    }

    @Transactional
    public HospitalResponse updateProfile(HospitalRequest request) {
        log.debug("Updating hospital profile for license number: {}", request.getHospitalLicenseNumber());
        
        Hospital hospital = hospitalRepository.findByHospitalLicenseNumber(request.getHospitalLicenseNumber())
            .orElseThrow(() -> new ResourceNotFoundException("Hospital profile not found for license number: " + request.getHospitalLicenseNumber()));

        updateHospitalFromRequest(hospital, request);
        Hospital updatedHospital = hospitalRepository.save(hospital);
        
        log.info("Updated hospital profile for license number: {}", request.getHospitalLicenseNumber());
        return mapToHospitalResponse(updatedHospital);
    }

    private void updateHospitalFromRequest(Hospital hospital, HospitalRequest request) {
        hospital.setEmail(request.getEmail());
        hospital.setHospitalName(request.getHospitalName());
        hospital.setIsAccredited(request.getIsAccredited());
        hospital.setSpecialtiesOffered(request.getSpecialtiesOffered());
        hospital.setYearsOfExperienceInHealthcare(request.getYearsOfExperienceInHealthcare());
        hospital.setSpecializedCareUnits(request.getSpecializedCareUnits());
    }

    private HospitalResponse mapToHospitalResponse(Hospital hospital) {
        if (hospital == null) return null;
        
        return HospitalResponse.builder()
            .id(hospital.getId())
            .hospitalLicenseNumber(hospital.getHospitalLicenseNumber())
            .email(hospital.getEmail())
            .hospitalName(hospital.getHospitalName())
            .isAccredited(hospital.getIsAccredited())
            .specialtiesOffered(hospital.getSpecialtiesOffered())
            .yearsOfExperienceInHealthcare(hospital.getYearsOfExperienceInHealthcare())
            .specializedCareUnits(hospital.getSpecializedCareUnits())
            .createdAt(formatDateTime(hospital.getCreatedAt()))
            .lastUpdatedAt(formatDateTime(hospital.getLastUpdatedAt()))
            .build();
    }
    
    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}