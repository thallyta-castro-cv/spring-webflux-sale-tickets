package com.reactive.saletickets.repositories;

import com.reactive.saletickets.models.Event;
import com.reactive.saletickets.models.EventTypeEnum;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
    Flux<Event> findByType(EventTypeEnum eventType);
}
