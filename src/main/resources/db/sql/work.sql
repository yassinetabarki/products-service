--liquibase formatted sql

--changeset yassine:work-1 dbms:postgresql
CREATE TABLE orders
(
  id                 SERIAL primary key,
  order_number       varchar(30),
  published_date     timestamp with time zone,
  created_by         varchar(255),
  created_date       timestamp with time zone,
  last_modified_by   varchar(255),
  last_modified_date timestamp with time zone
);
