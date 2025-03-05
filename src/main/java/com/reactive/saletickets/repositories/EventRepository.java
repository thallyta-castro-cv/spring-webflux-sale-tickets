package com.reactive.saletickets.repositories;

import com.reactive.saletickets.models.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
}
