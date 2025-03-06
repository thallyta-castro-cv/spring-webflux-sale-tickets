CREATE TABLE tickets (
    id BIGSERIAL NOT NULL,
    event_id BIGINT NOT NULL,
    type VARCHAR(30) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    total INT NOT NULL,

    PRIMARY KEY (id)
);
