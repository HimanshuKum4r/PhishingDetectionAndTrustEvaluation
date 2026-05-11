package com.Project.PhishingDetection.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class VirusTotalService {
    @Value("${VIRUSTOTAL_KEY}")
    private String apiKey;

    private final WebClient.Builder builder;

    public boolean isMalicious(String url){

        try{

            WebClient webClient =
                    builder.baseUrl(
                            "https://www.virustotal.com"
                    ).build();

            String response =
                    webClient.post()

                            .uri("/api/v3/urls")

                            .header("x-apikey",
                                    apiKey)

                            .bodyValue("url=" + url)

                            .retrieve()

                            .bodyToMono(String.class)

                            .block();
            System.out.println(response);

            return response != null &&
                    response.contains("malicious");

        }catch (Exception e){

            return false;
        }
    }

}
