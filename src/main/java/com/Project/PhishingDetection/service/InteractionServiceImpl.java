package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.Util.RedirectUtil;
import com.Project.PhishingDetection.Util.UrlUtil;
import com.Project.PhishingDetection.model.Interaction;
import com.Project.PhishingDetection.repository.InteractionRepository;
import com.Project.PhishingDetection.riskEngine.PhishingDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService {
    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private PhishingDetectionService phishingDetectionService;
    @Autowired
    private  TrustEvaluationService trustEvaluationService;

    public Interaction  processscan(String url){

        String domain = UrlUtil.extractDomain(url);

        int riskScore = phishingDetectionService.calculateRisk(url);

        int trustScore = trustEvaluationService.evaluateTrustScore(url,riskScore);

        String category;
        if (trustScore>70)
            category = "SAFE";
        else if (trustScore>40)
            category = "SUSPICIOUS";
        else
            category = "MALICIOUS";


        Interaction interaction = new Interaction();
        interaction.setOriginalUrl(url);
        interaction.setDomain(domain);
        interaction.setRiskScore(riskScore);
        interaction.setTrustScore(trustScore);
        interaction.setCategory(category);
        interaction.setRedirects(RedirectUtil.countRedirects(url));


        interaction.setExplanation("risk is evalauted using Url signals, redirects , and database based ");


        return interactionRepository.save(interaction);



    }
}
