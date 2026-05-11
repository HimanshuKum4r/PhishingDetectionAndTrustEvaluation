package com.Project.PhishingDetection.service;


import com.Project.PhishingDetection.dto.ScanRequestDTO;
import com.Project.PhishingDetection.dto.ScanResponseDTO;
import com.Project.PhishingDetection.model.Interaction;

public interface InteractionService {
    ScanResponseDTO scanUrl(ScanRequestDTO requestDTO);
}
