package com.reactive.saletickets.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("events")
@Getter
@Setter
public class Event {
    @id
    private Long id;
    private EventTypeEnum type;
    private String name;
    private LocalDate date;
    private String description;
}

