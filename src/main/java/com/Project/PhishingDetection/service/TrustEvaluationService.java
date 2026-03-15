package com.Project.PhishingDetection.service;


import com.Project.PhishingDetection.Util.UrlUtil;
import com.Project.PhishingDetection.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrustEvaluationService {
    @Autowired
    private   InteractionRepository interactionRepository;


    public int evaluateTrustScore(String url, int riskscore){
        int trustsccore = 100 - riskscore;

        long totalScans = interactionRepository.countByDomain(UrlUtil.extractDomain(url));

        if (totalScans>20)
            trustsccore-=10;

        Long maliciousHistory = interactionRepository.countByOriginalUrlAndCategory(url,"malicious");

        if (maliciousHistory>3)
            trustsccore-=25;

        if(trustsccore<0);

        return trustsccore;
    }
}
