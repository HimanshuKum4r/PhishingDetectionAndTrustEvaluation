package com.Project.PhishingDetection.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarningResponseDTO {

    private String status;

    private String message;

    private Integer riskScore;

    private String explanation;

    private String proceedUrl;

}
