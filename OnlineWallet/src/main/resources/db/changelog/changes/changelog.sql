-- liquibase formatted sql

--changeset 1 Valeriy Kryatov: operationType
CREATE TYPE operation_type AS ENUM (
    'DEPOSIT',
    'WITHDRAW');

--changeset 2 Valeriy Kryatov: wallets
CREATE TABLE wallets
(
    id      UUID PRIMARY KEY,
    balance NUMERIC NOT NULL
);

--changeset 3 Valeriy Kryatov: operations
CREATE TABLE operations
(
    id             BIGSERIAL PRIMARY KEY,
    operation      operation_type NOT NULL,
    operation_date TIMESTAMP        NOT NULL DEFAULT CURRENT_DATE,
    amount         NUMERIC          NOT NULL,
    wallets_id     UUID          NOT NULL,
    FOREIGN KEY (wallets_id) REFERENCES wallets (id)
);