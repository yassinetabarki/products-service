--liquibase formatted sql

--changeset yassine:add dbms:postgresql
ALTER TABLE products
DROP COLUMN IF EXISTS category_id,
ADD COLUMN category_id INTEGER,
DROP CONSTRAINT IF EXISTS fk_category_product,
ADD CONSTRAINT fk_category_product FOREIGN KEY (category_id) REFERENCES categories (id);