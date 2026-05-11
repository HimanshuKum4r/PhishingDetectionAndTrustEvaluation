package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.dto.RiskSignalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiExplanabilityService {

    @Value("${GEMINI_API}")
    private String apiKey;

    private final WebClient.Builder builder;

    public String generateExplanation(
            List<RiskSignalDTO> signals,
            int riskScore){

        try{

            WebClient webClient =
                    builder.baseUrl(
                            "https://generativelanguage.googleapis.com"
                    ).build();

            String signalText =
                    signals.stream()
                            .map(RiskSignalDTO::getSignal)
                            .collect(Collectors.joining(", "));

            String prompt =
                    """
                    You are a cybersecurity assistant.

                    Explain in simple user-friendly language
                    why this URL may be dangerous.

                    Risk Score:
                    """ + riskScore +

                            """
        
                            Detected Signals:
                            """ + signalText +

                            """
        
                            Keep explanation short,
                            clear and security-focused.
                            """;

            String requestBody =
                    """
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": "%s"
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(
                            prompt.replace("\"","\\\"")
                    );

            String response =
                    webClient.post()

                            .uri("/v1beta/models/gemini-pro:generateContent?key="
                                    + apiKey)

                            .header("Content-Type",
                                    "application/json")

                            .bodyValue(requestBody)

                            .retrieve()

                            .bodyToMono(String.class)

                            .block();

            return extractText(response);

        }catch (Exception e){

            return "Unable to generate AI explanation.";
        }
    }

    private String extractText(String response){

        try{

            int start =
                    response.indexOf("\"text\":") + 8;

            int end =
                    response.indexOf("\"", start);

            return response.substring(start,end);

        }catch (Exception e){

            return "AI explanation unavailable.";
        }
    }



}
