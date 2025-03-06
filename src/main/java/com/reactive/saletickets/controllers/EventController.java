package com.reactive.saletickets.controllers;

import com.reactive.saletickets.models.Event;
import com.reactive.saletickets.models.EventDto;
import com.reactive.saletickets.repositories.EventRepository;
import com.reactive.saletickets.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDto> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<EventDto> getById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @PostMapping
    public Mono<EventDto> register(@RequestBody EventDto dto) {
        return eventService.register(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return eventService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<EventDto> update(@PathVariable Long id, @RequestBody EventDto dto) {
        return eventService.update(id, dto);
    }

}
