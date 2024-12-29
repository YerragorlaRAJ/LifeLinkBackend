package com.example.LikeLink.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LikeLink.Exception.ResourceNotFoundException;
import com.example.LikeLink.Model.Ambulance;
import com.example.LikeLink.Repository.AmbulanceRepository;
import com.example.LikeLink.Repository.UserRepository;
import com.example.LikeLink.dto.request.AmbulanceRequest;
import com.example.LikeLink.dto.response.AmbulanceResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmbulanceService {
    @Autowired
    private final AmbulanceRepository ambulanceRepository;
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public AmbulanceResponse createProfile(AmbulanceRequest request, String email) {
        log.debug("Creating ambulance profile for email: {}", email);

        userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (ambulanceRepository.existsByEmail(email)) {
            throw new IllegalStateException("Profile already exists for user with email: " + email);
        }

        Ambulance ambulance = Ambulance.builder()
            .fullName(request.getFullName())
            .dateOfBirth(request.getDateOfBirth())
            .phoneNumber(request.getPhoneNumber())
            .currentAddress(request.getCurrentAddress())
            .driverLicenseNumber(request.getDriverLicenseNumber())
            .licenseType(request.getLicenseType())
            .licenseExpiryDate(request.getLicenseExpiryDate())
            .yearsOfDrivingExperience(request.getYearsOfDrivingExperience())
            .experienceWithEmergencyVehicles(request.getExperienceWithEmergencyVehicles())
            .vehicleRegistrationNumber(request.getVehicleRegistrationNumber())
            .hasAirConditioning(request.getHasAirConditioning())
            .hasOxygenCylinderHolder(request.getHasOxygenCylinderHolder())
            .hasStretcher(request.getHasStretcher())
            .insurancePolicyNumber(request.getInsurancePolicyNumber())
            .insuranceExpiryDate(request.getInsuranceExpiryDate())
            .isAvailable(false)
            .isVerified(false)
            .email(email)
            .createdAt(LocalDateTime.now())
            .lastUpdatedAt(LocalDateTime.now())
            .build();

        Ambulance savedAmbulance = ambulanceRepository.save(ambulance);
        log.info("Created ambulance profile for email: {}", email);
        
        return mapToAmbulanceResponse(savedAmbulance);
    
    }

    public AmbulanceResponse getProfile(String email) {
        log.debug("Fetching ambulance profile for email: {}", email);
        
        Ambulance ambulance = ambulanceRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Ambulance profile not found for email: " + email));
            
        return mapToAmbulanceResponse(ambulance);
    }

    @Transactional
    public AmbulanceResponse updateProfile(AmbulanceRequest request, String email) {
        log.debug("Updating ambulance profile for email: {}", email);
        
        Ambulance ambulance = ambulanceRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Ambulance profile not found for email: " + email));

        updateAmbulanceFromRequest(ambulance, request);
        Ambulance updatedAmbulance = ambulanceRepository.save(ambulance);
        
        log.info("Updated ambulance profile for email: {}", email);
        return mapToAmbulanceResponse(updatedAmbulance);
    }

    @Transactional
    public AmbulanceResponse updateAvailability(boolean isAvailable, String email) {
        log.debug("Updating availability status for email: {}", email);
        
        Ambulance ambulance = ambulanceRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Ambulance profile not found for email: " + email));

        ambulance.setIsAvailable(isAvailable);
        Ambulance updatedAmbulance = ambulanceRepository.save(ambulance);
        
        log.info("Updated availability status for email: {}", email);
        return mapToAmbulanceResponse(updatedAmbulance);
    }

    private void updateAmbulanceFromRequest(Ambulance ambulance, AmbulanceRequest request) {
        ambulance.setFullName(request.getFullName());
        ambulance.setDateOfBirth(request.getDateOfBirth());
        ambulance.setPhoneNumber(request.getPhoneNumber());
        ambulance.setCurrentAddress(request.getCurrentAddress());
        ambulance.setDriverLicenseNumber(request.getDriverLicenseNumber());
        ambulance.setLicenseType(request.getLicenseType());
        ambulance.setLicenseExpiryDate(request.getLicenseExpiryDate());
        ambulance.setYearsOfDrivingExperience(request.getYearsOfDrivingExperience());
        ambulance.setExperienceWithEmergencyVehicles(request.getExperienceWithEmergencyVehicles());
        ambulance.setVehicleRegistrationNumber(request.getVehicleRegistrationNumber());
        ambulance.setHasAirConditioning(request.getHasAirConditioning());
        ambulance.setHasOxygenCylinderHolder(request.getHasOxygenCylinderHolder());
        ambulance.setHasStretcher(request.getHasStretcher());
        ambulance.setInsurancePolicyNumber(request.getInsurancePolicyNumber());
        ambulance.setInsuranceExpiryDate(request.getInsuranceExpiryDate());
    }

    private AmbulanceResponse mapToAmbulanceResponse(Ambulance ambulance) {
        if (ambulance == null) return null;
        
        return AmbulanceResponse.builder()
            .id(ambulance.getId()) 
            .fullName(ambulance.getFullName())
            .dateOfBirth(ambulance.getDateOfBirth())
            .phoneNumber(ambulance.getPhoneNumber())
            .currentAddress(ambulance.getCurrentAddress())
            .driverLicenseNumber(ambulance.getDriverLicenseNumber())
            .licenseType(ambulance.getLicenseType())
            .licenseExpiryDate(ambulance.getLicenseExpiryDate())
            .yearsOfDrivingExperience(ambulance.getYearsOfDrivingExperience())
            .experienceWithEmergencyVehicles(ambulance.getExperienceWithEmergencyVehicles())
            .vehicleRegistrationNumber(ambulance.getVehicleRegistrationNumber())
            .hasAirConditioning(ambulance.getHasAirConditioning())
            .hasOxygenCylinderHolder(ambulance.getHasOxygenCylinderHolder())
            .hasStretcher(ambulance.getHasStretcher())
            .insurancePolicyNumber(ambulance.getInsurancePolicyNumber())
            .insuranceExpiryDate(ambulance.getInsuranceExpiryDate())
            .isAvailable(ambulance.getIsAvailable())
            .isVerified(ambulance.getIsVerified())
            .createdAt(formatDateTime(ambulance.getCreatedAt()))
            .lastUpdatedAt(formatDateTime(ambulance.getLastUpdatedAt()))
            .build();
    }
    
    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
