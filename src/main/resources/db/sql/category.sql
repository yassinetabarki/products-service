--liquibase formatted sql

--changeset yassine:category-1 dbms:postgresql
CREATE TABLE categories
(
    id                  SERIAL primary key,
    name                varchar(50),
    description         varchar(255),
    created_date        timestamp with time zone,
    last_modified_date  timestamp with time zone
);
