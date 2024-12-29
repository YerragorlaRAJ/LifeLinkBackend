package com.example.LikeLink.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ambulances")
public class Ambulance {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
}