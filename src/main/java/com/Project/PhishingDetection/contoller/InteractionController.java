package com.Project.PhishingDetection.contoller;

import com.Project.PhishingDetection.dto.ScanRequestDTO;
import com.Project.PhishingDetection.dto.ScanResponseDTO;
import com.Project.PhishingDetection.model.Interaction;
import com.Project.PhishingDetection.service.InteractionService;
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
    public ResponseEntity<ScanResponseDTO>  scanUrl(@Valid @RequestBody ScanRequestDTO request){

        ScanResponseDTO response = interactionService.scanUrl(request);
        return ResponseEntity.ok(response);



    }


}
