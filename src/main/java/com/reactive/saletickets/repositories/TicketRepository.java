package com.reactive.saletickets.repositories;

import com.reactive.saletickets.models.entities.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, Long> {
}

