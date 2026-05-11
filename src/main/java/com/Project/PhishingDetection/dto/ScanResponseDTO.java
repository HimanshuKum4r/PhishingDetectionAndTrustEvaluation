package com.Project.PhishingDetection.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScanResponseDTO {

    private String interactionToken;

    private  String redirectUrl;
    private  Integer riskScore;
//
//    private  Integer trustScore;

    private String category;

    private  String message;

}
