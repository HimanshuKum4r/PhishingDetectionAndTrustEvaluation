package com.Project.PhishingDetection.riskEngine;

import com.Project.PhishingDetection.Util.RedirectUtil;
import com.Project.PhishingDetection.dto.RiskSignalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RedirectEngine {
    public List<RiskSignalDTO> evaluate(String url){

        List<RiskSignalDTO> signals =
                new ArrayList<>();

        int redirects =
                RedirectUtil.countRedirects(url);

        if(redirects >= 3){

            signals.add(
                    new RiskSignalDTO(
                            "Multiple redirect chain",
                            20
                    )
            );
        }

        return signals;
  }

 }
