package com.Project.PhishingDetection.dto;

import lombok.Data;

@Data
public class ScanResponseDTO {

    private Long interactionId;

    private  Integer riskScore;

    private  Integer trustScore;

    private String category;

    private  String explanation;

}
