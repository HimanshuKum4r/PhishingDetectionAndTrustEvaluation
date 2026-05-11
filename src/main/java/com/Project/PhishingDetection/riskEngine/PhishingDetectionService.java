package com.Project.PhishingDetection.riskEngine;

import com.Project.PhishingDetection.Util.KeywordUtil;
import com.Project.PhishingDetection.dto.RiskSignalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhishingDetectionService {
    private final ThreatIntelRiskEngine threatIntelRiskEngine;

    private final UrlStructureRiskEngine urlStructureRiskEngine;

    private final RedirectEngine redirectEngine;

    private final KeywordUtil keywordUtil;


    public int calculateRisk(String url){
        List<RiskSignalDTO> signals = new ArrayList<>();
        signals.addAll(threatIntelRiskEngine.evaluate(url));

        signals.addAll(keywordUtil.KeywordRiskscore(url));

        signals.addAll(redirectEngine.evaluate(url));

        signals.addAll(urlStructureRiskEngine.evaluate(url));


        int riskScore  = signals.stream().mapToInt(RiskSignalDTO::getWeight).sum();

        return  Math.min(riskScore,100);

    }


}
