package com.reactive.saletickets.controllers;

import com.reactive.saletickets.models.dtos.EventDto;
import com.reactive.saletickets.services.EventService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final Sinks.Many<EventDto> eventSink;

    public EventController(EventService service) {
        this.eventService = service;
        this.eventSink = Sinks.many().multicast().onBackpressureBuffer();
    }


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDto> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<EventDto> getById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @GetMapping(value = "/category/{type}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventDto> getByType(@PathVariable String type) {
        return Flux.merge(eventService.getByType(type), eventSink.asFlux())
                .delayElements(Duration.ofSeconds(4));
    }

    @PostMapping
    public Mono<EventDto> register(@RequestBody EventDto dto) {
        return eventService.register(dto)
                .doOnSuccess(eventSink::tryEmitNext);
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
