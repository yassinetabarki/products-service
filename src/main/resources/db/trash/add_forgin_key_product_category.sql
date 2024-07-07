--liquibase formatted sql

--changeset yassine:add dbms:postgresql
--ALTER TABLE products ADD COLUMN category_id SERIAL;
--ALTER TABLE products DROP CONSTRAINT IF EXISTS category_product_fk;
--ALTER TABLE products ADD CONSTRAINT category_product_fk FOREIGN KEY (category_id) REFERENCES categories (id);
