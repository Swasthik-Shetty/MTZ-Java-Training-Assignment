--liquibase formatted sql

--precondition-name precondition-attribute:value

--changeset Swasthik:1

create table if not exists contact_table (
    id UUID primary key,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    mobile varchar(10),
    deleted boolean default false
);

--changeset Swasthik:2
CREATE TABLE if not exists user_table (
    id UUID PRIMARY KEY,
    user_name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    mobile VARCHAR(20),
    deleted BOOLEAN DEFAULT FALSE
);