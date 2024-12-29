package com.example.LikeLink.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class HospitalRequest {
    @NotBlank(message = "Hospital license number is required")
    private String hospitalLicenseNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

    @NotNull(message = "Accreditation status is required")
    private Boolean isAccredited;

    @NotEmpty(message = "At least one specialty must be offered")
    private List<String> specialtiesOffered;

    @NotNull(message = "Years of experience in healthcare is required")
    @Min(value = 0, message = "Years of experience cannot be negative")
    private Integer yearsOfExperienceInHealthcare;

    private List<String> specializedCareUnits;
}