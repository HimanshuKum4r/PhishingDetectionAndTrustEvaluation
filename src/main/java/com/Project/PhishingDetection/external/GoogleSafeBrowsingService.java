package com.Project.PhishingDetection.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleSafeBrowsingService {
    @Value("${SAFE_BROWSING_KEY}")
    private String apiKey;

    private final WebClient.Builder builder;

    public boolean isUnsafe(String url) {

        try {

            WebClient webClient =
                    builder.baseUrl(
                            "https://safebrowsing.googleapis.com"
                    ).build();

            Map<String, Object> requestBody =
                    Map.of(
                            "client",
                            Map.of(
                                    "clientId",
                                    "trust-platform",
                                    "clientVersion",
                                    "1.0"
                            ),

                            "threatInfo",
                            Map.of(
                                    "threatTypes",
                                    List.of(
                                            "MALWARE",
                                            "SOCIAL_ENGINEERING"
                                    ),

                                    "platformTypes",
                                    List.of(
                                            "ANY_PLATFORM"
                                    ),

                                    "threatEntryTypes",
                                    List.of("URL"),

                                    "threatEntries",
                                    List.of(
                                            Map.of(
                                                    "url",
                                                    url
                                            )
                                    )
                            )
                    );

            String response =
                    webClient.post()
                            .uri("/v4/threatMatches:find?key="
                                    + apiKey)

                            .bodyValue(requestBody)

                            .retrieve()

                            .bodyToMono(String.class)

                            .block();

            System.out.println(response);

            return response != null &&
                    response.contains("matches");

        } catch (Exception e) {

            return false;
        }
    }

}
