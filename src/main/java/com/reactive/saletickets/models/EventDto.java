package com.reactive.saletickets.models;

import java.time.LocalDate;

public record EventDto(Long id,
        EventTypeEnum type,
        String name,
        LocalDate date,
        String description) {

    public static EventDto toDto(Event event){
        return new EventDto(event.getId(),
                            event.getType(),
                            event.getName(),
                            event.getDate(),
                            event.getDescription());
    }

    public Event toEntity() {
        Event event = new Event();
        event.setId(this.id);
        event.setName(this.name);
        event.setType(this.type);
        event.setDate(this.date);
        event.setDescription(this.description);
        return event;
    }
}
