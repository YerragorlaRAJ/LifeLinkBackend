package com.example.LikeLink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.LikeLink.Service.AmbulanceService;
import com.example.LikeLink.dto.request.AmbulanceRequest;
import com.example.LikeLink.dto.response.AmbulanceResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/ambulance")
@PreAuthorize("hasRole('AMBULANCE_DRIVER')")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AmbulanceController {

    @Autowired
    private final AmbulanceService ambulanceService;

    @PostMapping("/profile")
    public ResponseEntity<AmbulanceResponse> createProfile(
            @Valid @RequestBody AmbulanceRequest ambulanceRequest,
            Authentication authentication) {
        log.info("Creating profile for user: {}", authentication.getName());
        AmbulanceResponse savedAmbulance = ambulanceService.createProfile(ambulanceRequest, authentication.getName());
        return ResponseEntity.ok(savedAmbulance);
    }

    @GetMapping("/profile")
    public ResponseEntity<AmbulanceResponse> getProfile(Authentication authentication) {
        log.info("Fetching profile for user: {}", authentication.getName());
        AmbulanceResponse ambulance = ambulanceService.getProfile(authentication.getName());
        return ResponseEntity.ok(ambulance);
    }

    @PutMapping("/profile")
    public ResponseEntity<AmbulanceResponse> updateProfile(
            @Valid @RequestBody AmbulanceRequest ambulanceRequest,
            Authentication authentication) {
        log.info("Updating profile for user: {}", authentication.getName());
        AmbulanceResponse updatedAmbulance = ambulanceService.updateProfile(ambulanceRequest, authentication.getName());
        return ResponseEntity.ok(updatedAmbulance);
    }

    @PatchMapping("/availability")
    public ResponseEntity<AmbulanceResponse> updateAvailability(
            @RequestParam boolean isAvailable,
            Authentication authentication) {
        log.info("Updating availability status for user: {}", authentication.getName());
        return ResponseEntity.ok(ambulanceService.updateAvailability(isAvailable, authentication.getName()));
    }
}