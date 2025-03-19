package com.reactive.saletickets.models.dtos;

import java.util.List;

public record TranslationDto(List<TextDto> translations) {

    public String getText() {
        return translations.getFirst().text();
    }
}
