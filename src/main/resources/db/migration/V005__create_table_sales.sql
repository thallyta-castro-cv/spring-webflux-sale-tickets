create table sales (
    id bigserial not null,
    ticket_id bigint not null,
    total_quantity int not null,

    primary key(id)
);
