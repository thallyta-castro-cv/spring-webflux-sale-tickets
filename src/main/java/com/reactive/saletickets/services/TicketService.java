package com.reactive.saletickets.services;

import com.reactive.saletickets.models.dtos.PurchaseDto;
import com.reactive.saletickets.models.dtos.TicketDto;
import com.reactive.saletickets.models.entities.Sale;
import com.reactive.saletickets.repositories.SaleRepository;
import com.reactive.saletickets.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Flux<TicketDto> getAll() {
        return ticketRepository.findAll()
                .map(TicketDto::toDto);
    }

    public Mono<TicketDto> getById(Long id) {
        return ticketRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(TicketDto::toDto);
    }

    public Mono<TicketDto> create(TicketDto dto) {
        return ticketRepository.save(dto.toEntity())
                .map(TicketDto::toDto);
    }

    public Mono<Void> delete(Long id) {
        return ticketRepository.findById(id)
                .flatMap(ticketRepository::delete);
    }

    public Mono<TicketDto> update(Long id, TicketDto dto) {
        return ticketRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Event ID not found.")))
                .flatMap(ticket -> {
                    ticket.setEventId(dto.eventId());
                    ticket.setType(dto.type());
                    ticket.setPrice(dto.price());
                    ticket.setTotal(dto.total());
                    return ticketRepository.save(ticket);
                })
                .map(TicketDto::toDto);
    }

    @Transactional
    public Mono<TicketDto> purchase(PurchaseDto dto) {
        return ticketRepository.findById(dto.ticketId())
                .flatMap(ticket -> {
                    Sale sale = new Sale();
                    sale.setTicketId(ticket.getId());
                    sale.setTotal(dto.total());
                    return saleRepository.save(sale).then(Mono.defer(() -> {
                        ticket.setTotal(ticket.getTotal() - dto.total());
                        return ticketRepository.save(ticket);
                    }));
                }).map(TicketDto::toDto);
    }

}

