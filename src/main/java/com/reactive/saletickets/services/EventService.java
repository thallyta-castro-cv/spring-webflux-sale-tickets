package com.reactive.saletickets.services;

import com.reactive.saletickets.models.EventDto;
import com.reactive.saletickets.models.EventTypeEnum;
import com.reactive.saletickets.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Flux<EventDto> getAll() {
        return repository.findAll()
                .map(EventDto::toDto);
    }

    public Mono<EventDto> getById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(EventDto::toDto);
    }

    public Mono<EventDto> register(EventDto dto) {
        return repository.save(dto.toEntity())
                .map(EventDto::toDto);
    }

    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .flatMap(repository::delete);
    }

    public Mono<EventDto> update(Long id, EventDto dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Event ID not found.")))
                .flatMap(existingEvent -> {
                    existingEvent.setType(dto.type());
                    existingEvent.setName(dto.name());
                    existingEvent.setDate(dto.date());
                    existingEvent.setDescription(dto.description());
                    return repository.save(existingEvent);
                })
                .map(EventDto::toDto);
    }

    public Flux<EventDto> getByType(String type) {
        EventTypeEnum eventType = EventTypeEnum.valueOf(type.toUpperCase());
        return repository.findByType(eventType)
                .map(EventDto::toDto);
    }

}
