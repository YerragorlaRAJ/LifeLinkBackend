package com.example.LikeLink.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class AmbulanceResponse {
    private String id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String currentAddress;
    private String driverLicenseNumber;
    private String licenseType;
    private LocalDate licenseExpiryDate;
    private Integer yearsOfDrivingExperience;
    private Boolean experienceWithEmergencyVehicles;
    private String vehicleRegistrationNumber;
    private Boolean hasAirConditioning;
    private Boolean hasOxygenCylinderHolder;
    private Boolean hasStretcher;
    private String insurancePolicyNumber;
    private LocalDate insuranceExpiryDate;
    private Boolean isAvailable;
    private Boolean isVerified;
    private String createdAt;
    private String lastUpdatedAt;
}