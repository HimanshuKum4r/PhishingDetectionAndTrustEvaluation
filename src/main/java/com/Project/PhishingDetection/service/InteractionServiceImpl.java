package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.Util.UrlUtil;
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
        int riskcore = phishingDetectionService.calculateRisk(request.getUrl());

        interaction.setRiskScore(riskcore);
        interactionRepository.save(interaction);

        return ScanResponseDTO.builder()
                .interactionToken(token)
                .riskScore(riskcore)
                .redirectUrl("/r/" + token)
                .category("PENDING")
                .message("Risk evaluation in progress")
                .build();

    }
}
