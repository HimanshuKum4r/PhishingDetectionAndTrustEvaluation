package com.Project.PhishingDetection.service;

import com.Project.PhishingDetection.dto.RiskSignalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

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

                            .uri("/v1beta/models/gemini-2.5-flash:generateContent?key="
                                    + apiKey)

                            .header("Content-Type",
                                    "application/json")

                            .bodyValue(requestBody)

                            .retrieve()

                            .bodyToMono(String.class)

                            .block();

            String explanation = extractText(response);
            explanation = explanation
                    .replace("\\n", " ")
                    .replace("\n", " ")
                    .replace("**", "")
                    .replace("*", "")
                    .replaceAll("\\s+", " ")
                    .trim();

            return explanation;

        }catch (Exception e){

            return "Unable to generate AI explanation.";
        }
    }

    private String extractText(String response){

        try{

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(response);

            return root
                    .get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text")
                    .asString();

        }catch (Exception e){

            e.printStackTrace();

            return "AI explanation unavailable.";
        }
    }



}
