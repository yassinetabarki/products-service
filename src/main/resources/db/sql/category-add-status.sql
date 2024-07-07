--liquibase formatted sql

--changeset category:add dbms:postgresql

ALTER TABLE categories
DROP COLUMN IF EXISTS status,
ADD COLUMN status VARCHAR(20);