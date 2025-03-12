package com.reactive.saletickets.repositories;

import com.reactive.saletickets.models.entities.Sale;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SaleRepository extends ReactiveCrudRepository<Sale, Long> {
}

