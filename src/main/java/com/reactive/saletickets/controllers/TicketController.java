package com.reactive.saletickets.controllers;

import com.reactive.saletickets.models.dtos.PurchaseDto;
import com.reactive.saletickets.models.dtos.TicketDto;
import com.reactive.saletickets.services.TicketService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;
    private final Sinks.Many<TicketDto> ticketSink;

    public TicketController(TicketService service) {
        this.service = service;
        this.ticketSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping
    public Flux<TicketDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<TicketDto> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Mono<TicketDto> create(@RequestBody TicketDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<TicketDto> update(@PathVariable Long id, @RequestBody TicketDto dto) {
        return service.update(id, dto);
    }

    @PostMapping("/purchase")
    public Mono<TicketDto> purchase(@RequestBody PurchaseDto dto) {
        return service.purchase(dto)
                .doOnSuccess(i -> ticketSink.tryEmitNext(i));
    }

    @GetMapping(value = "/{id}/available", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TicketDto> totalAvailable(@PathVariable Long id) {
        return Flux.merge(service.getById(id), ticketSink.asFlux())
                .delayElements(Duration.ofSeconds(4));
    }


}

