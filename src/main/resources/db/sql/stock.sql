--liquibase formatted sql


--changeset yassine:stock-1 dbms:postgresql
CREATE TABLE stock
(
  id                 SERIAL primary key,
  product_id         integer not null ,
  quantity           integer default(0),
  created_date       timestamp with time zone,
  last_modified_date timestamp with time zone,
  constraint fk_product foreign key(product_id) references products(id)

);
