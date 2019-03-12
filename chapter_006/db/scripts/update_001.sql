CREATE TABLE items (
    id SERIAL   PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(2000),
    created     TIMESTAMP
);