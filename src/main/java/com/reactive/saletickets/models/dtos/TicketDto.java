package com.reactive.saletickets.models.dtos;

import com.reactive.saletickets.models.entities.Ticket;
import com.reactive.saletickets.models.enuns.TicketTypeEnum;

public record TicketDto(Long id,
                        Long eventId,
                        TicketTypeEnum type,
                        Double price,
                        int total) {

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket.getId(), ticket.getEventId(),
                ticket.getType(), ticket.getPrice(), ticket.getTotal());
    }

    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setId(this.id);
        ticket.setEventId(this.eventId);
        ticket.setType(this.type);
        ticket.setPrice(this.price);
        ticket.setTotal(this.total);
        return ticket;
    }
}

