package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.Util.CategoryUtil;
import com.Project.PhishingDetection.Util.UrlUtil;
import com.Project.PhishingDetection.dto.RiskAnalysisDTO;
import com.Project.PhishingDetection.dto.ScanRequestDTO;
import com.Project.PhishingDetection.dto.ScanResponseDTO;
import com.Project.PhishingDetection.model.Interaction;
import com.Project.PhishingDetection.repository.InteractionRepository;
import com.Project.PhishingDetection.riskEngine.PhishingDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    private final CategoryUtil categoryUtil;
    private final  AiExplanabilityService aiExplanabilityService;
    private final InteractionRepository interactionRepository;
    private final PhishingDetectionService phishingDetectionService;

    @Override
    public ScanResponseDTO scanUrl(ScanRequestDTO request) {
        String token =
                UUID.randomUUID().toString();

        String domain =
                UrlUtil.extractDomain(
                        request.getUrl());

        Interaction interaction =
                Interaction.builder()
                        .interactionToken(token)
                        .originalUrl(request.getUrl())
                        .domain(domain)
                        .source(request.getSource())
                        .category("PENDING")
                        .warningBypassed(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        interactionRepository.save(interaction);
        RiskAnalysisDTO result = phishingDetectionService.calculateRisk(request.getUrl());

        interaction.setRiskScore(result.getRiskScore());

        String explanation = aiExplanabilityService.generateExplanation(result.getSignals(), result.getRiskScore());

        interaction.setExplanation(explanation);

        interaction.setCategory(categoryUtil.classify(result.getRiskScore()));

        interactionRepository.save(interaction);

        return ScanResponseDTO.builder()
                .interactionToken(token)
                .riskScore(result.getRiskScore())
                .redirectUrl("/r/" + token)
                .category(categoryUtil.classify(result.getRiskScore()))
                .message(explanation)
                .build();

    }
}
