package com.example.LikeLink.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AmbulanceRequest {
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
}