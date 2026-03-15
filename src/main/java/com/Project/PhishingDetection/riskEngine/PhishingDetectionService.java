package com.Project.PhishingDetection.riskEngine;

import com.Project.PhishingDetection.Util.KeywordUtil;
import com.Project.PhishingDetection.Util.RedirectUtil;
import org.springframework.stereotype.Service;

@Service
public class PhishingDetectionService {

    public  int calculateRisk(String url){

        int riskScore = 0;

        if(url.length()>80)
            riskScore+=10;

        if (url.contains("@"))
            riskScore+=10;

        if (url.contains("bit.ly") || url.contains("tinyurl"))
            riskScore+=20;

        if (!url.startsWith("https"))
            riskScore+=15;

        int redirects = RedirectUtil.countRedirects(url);
        if (redirects>=3)
            riskScore+=20;

        riskScore+= KeywordUtil.KeywordRiskscore(url);


        return Math.min(riskScore,100);

    }


}
