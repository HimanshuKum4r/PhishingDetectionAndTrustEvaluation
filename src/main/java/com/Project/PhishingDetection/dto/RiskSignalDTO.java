package com.Project.PhishingDetection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskSignalDTO {

    private String signal;

    private int weight;
}
