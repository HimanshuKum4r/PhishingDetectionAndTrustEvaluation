package com.Project.PhishingDetection.Util;

import org.springframework.stereotype.Service;

@Service
public class CategoryUtil {
    public String classify(
            int riskScore){

        if(riskScore >= 70){

            return "MALICIOUS";
        }

        if(riskScore >= 40){

            return "SUSPICIOUS";
        }

        return "SAFE";
    }
}
