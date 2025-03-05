CREATE TABLE events (
    id BIGSERIAL NOT NULL,
    type VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    date DATE,
    description VARCHAR(200) NOT NULL,

    PRIMARY KEY (id)
);
