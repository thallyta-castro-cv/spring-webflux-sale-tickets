package com.reactive.saletickets.client;

import com.reactive.saletickets.models.dtos.TranslationDto;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DeepLClient {

    public static Mono<String> getTranslation(String text, String language) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api-free.deepl.com/v2/translate")
                .build();

        MultiValueMap<String, String> req = new LinkedMultiValueMap<>();

        req.add("text", text);
        req.add("target_lang", language);

        return webClient.post()
                .header("Authorization", "DeepL-Auth-Key " + "YOUR-API-KEY-HERE")
                .body(BodyInserters.fromFormData(req))
                .retrieve()
                .bodyToMono(TranslationDto.class)
                .map(TranslationDto::getText);
    }
}

