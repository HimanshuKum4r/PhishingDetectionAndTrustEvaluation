package com.Project.PhishingDetection.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RiskAnalysisDTO {

    private int riskScore;

    private List<RiskSignalDTO> signals;

}
