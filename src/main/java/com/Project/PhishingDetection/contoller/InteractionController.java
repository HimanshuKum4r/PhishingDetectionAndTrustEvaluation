package com.Project.PhishingDetection.contoller;

import com.Project.PhishingDetection.dto.ScanRequestDto;
import com.Project.PhishingDetection.dto.ScanResponseDTO;
import com.Project.PhishingDetection.model.Interaction;
import com.Project.PhishingDetection.service.InteractionService;
import com.Project.PhishingDetection.service.InteractionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;


    @PostMapping("/scan")
    public ResponseEntity<ScanResponseDTO>  scanUrl(@Valid @RequestBody ScanRequestDto request){

        Interaction interaction = interactionService.processscan(request.getUrl());

        ScanResponseDTO response = new ScanResponseDTO();
        response.setInteractionId(interaction.getInteractionId());
        response.setRiskScore(interaction.getRiskScore());
        response.setTrustScore(interaction.getTrustScore());
        response.setCategory(interaction.getCategory());
        response.setExplanation(interaction.getExplanation());


        return ResponseEntity.ok(response);



    }


}
