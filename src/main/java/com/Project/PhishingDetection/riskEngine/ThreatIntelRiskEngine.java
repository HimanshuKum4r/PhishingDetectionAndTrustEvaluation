package com.Project.PhishingDetection.riskEngine;

import com.Project.PhishingDetection.dto.RiskSignalDTO;
import com.Project.PhishingDetection.external.GoogleSafeBrowsingService;
import com.Project.PhishingDetection.external.PhishTankService;
import com.Project.PhishingDetection.external.VirusTotalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ThreatIntelRiskEngine {
    private final GoogleSafeBrowsingService googleSafeBrowsingService;

    private final PhishTankService phishTankService;

    private final VirusTotalService virusTotalService;

    public List<RiskSignalDTO> evaluate(String url){
        List<RiskSignalDTO>  signals = new ArrayList<>();
        if (googleSafeBrowsingService.isUnsafe(url)){
            signals.add(new RiskSignalDTO("google safe browsing flagged url",40));
        }

        if (virusTotalService.isMalicious(url)){
            signals.add(new RiskSignalDTO("virusTotal malicious reputation",35));
        }
        if (phishTankService.isKnownPhishing(url)){
            signals.add(new RiskSignalDTO("known phishing Url in PhishTank",40));
        }

        return signals;
    }
}
