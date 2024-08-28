--liquibase formatted sql

--precondition-name precondition-attribute:value

--changeset Swasthik:3 tag:version3
ALTER TABLE contacts
ADD COLUMN description VARCHAR(255);