package com.Project.PhishingDetection.riskEngine;

import com.Project.PhishingDetection.Util.KeywordUtil;
import com.Project.PhishingDetection.Util.RedirectUtil;
import com.Project.PhishingDetection.dto.RiskSignalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlStructureRiskEngine {

    public  List<RiskSignalDTO> evaluate(String url){

        List<RiskSignalDTO> signals = new ArrayList<>();



        if(url.length()>80)
            signals.add(
                    new RiskSignalDTO("Very long URL", 10));

        if (url.contains("@"))
            signals.add(
                    new RiskSignalDTO("contains @ symbol", 10));

        if (url.contains("bit.ly") || url.contains("tinyurl"))
            signals.add(
                    new RiskSignalDTO("url contains bit.ly and tinyurl", 10));

        if (!url.startsWith("https"))
            signals.add(
                    new RiskSignalDTO("non HTTPS URL", 15));


        return signals;

    }


}
