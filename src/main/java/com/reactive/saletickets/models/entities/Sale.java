package com.reactive.saletickets.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("sales")
@Getter
@Setter
public class Sale {

    @Id
    private Long id;
    private Long ticketId;
    private int total;
}

