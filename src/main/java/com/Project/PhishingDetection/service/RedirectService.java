package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.dto.WarningResponseDTO;
import com.Project.PhishingDetection.model.Interaction;
import com.Project.PhishingDetection.repository.InteractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class RedirectService {


    private final InteractionRepository repository;

    public ResponseEntity<?> handleRedirect(
            String token){

        Interaction interaction =
                repository.findByInteractionToken(token)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Invalid token"
                                )
                        );

        String category =
                interaction.getCategory();


        if("SAFE".equals(category)){

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(
                            URI.create(
                                    interaction.getOriginalUrl()
                            )
                    )
                    .build();
        }


        if("SUSPICIOUS".equals(category)){

            WarningResponseDTO response =
                    WarningResponseDTO.builder()
                            .status("WARNING")
                            .message(
                                    "This link may be unsafe."
                            )
                            .riskScore(
                                    interaction.getRiskScore()
                            )
                            .explanation(
                                    interaction.getExplanation()
                            )
                            .proceedUrl(
                                    "/r/proceed/" +
                                            interaction.getInteractionToken()
                            )
                            .build();

            return ResponseEntity.ok(response);
        }


        if("MALICIOUS".equals(category)){

            WarningResponseDTO response =
                    WarningResponseDTO.builder()
                            .status("BLOCKED")
                            .message(
                                    "Blocked malicious link."
                            )
                            .riskScore(
                                    interaction.getRiskScore()
                            )
                            .explanation(
                                    interaction.getExplanation()
                            )
                            .build();

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(response);
        }

        return ResponseEntity
                .badRequest()
                .body("Unknown category");
    }

    public ResponseEntity<?> proceed(
            String token){

        Interaction interaction =
                repository.findByInteractionToken(token)
                        .orElseThrow();

        // NEVER allow malicious override
        if("MALICIOUS".equals(
                interaction.getCategory())){

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Blocked malicious link.");
        }

        interaction.setWarningBypassed(true);

        repository.save(interaction);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(
                        URI.create(
                                interaction.getOriginalUrl()
                        )
                )
                .build();
    }



}
