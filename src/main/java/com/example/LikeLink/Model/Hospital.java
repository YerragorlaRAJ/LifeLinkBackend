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

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "hospitals")
public class Hospital {
    @Id
    private String id; 

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String hospitalLicenseNumber;

    private String hospitalName;
    private Boolean isAccredited;
    private List<String> specialtiesOffered;
    private Integer yearsOfExperienceInHealthcare;
    private List<String> specializedCareUnits;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
}