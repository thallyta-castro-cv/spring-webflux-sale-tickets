package com.reactive.saletickets.models.entities;

import com.reactive.saletickets.models.enuns.TicketTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tickets")
@Getter
@Setter
public class Ticket {
    @Id
    private Long id;
    private Long eventId;
    private TicketTypeEnum type;
    private Double price;
    private int total;
}

