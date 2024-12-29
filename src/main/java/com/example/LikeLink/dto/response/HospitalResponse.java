package com.example.LikeLink.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class HospitalResponse {
    private String id;
    private String hospitalLicenseNumber;
    private String email;
    private String hospitalName;
    private Boolean isAccredited;
    private List<String> specialtiesOffered;
    private Integer yearsOfExperienceInHealthcare;
    private List<String> specializedCareUnits;
    private String createdAt;
    private String lastUpdatedAt;
}