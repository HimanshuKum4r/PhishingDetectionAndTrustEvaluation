package com.Project.PhishingDetection.external;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PhishTankService {
    private final WebClient.Builder builder;

    public boolean isKnownPhishing(String url){

        try{

            WebClient webClient =
                    builder.baseUrl(
                            "https://checkurl.phishtank.com"
                    ).build();

            String response =
                    webClient.post()

                            .bodyValue(
                                    "url=" + url +
                                            "&format=json"
                            )

                            .retrieve()

                            .bodyToMono(String.class)

                            .block();

            System.out.println(response);

            return response != null &&
                    response.contains("\"valid\":true");

        }catch (Exception e){

            return false;
        }
    }

}

