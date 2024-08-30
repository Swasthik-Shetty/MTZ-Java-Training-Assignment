--liquibase formatted sql

--precondition-name precondition-attribute:value

--changeset Swasthik:1
CREATE TABLE if not exists user_table (
    id UUID PRIMARY KEY,
    user_name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    mobile VARCHAR(20),
    deleted BOOLEAN DEFAULT FALSE
);


--changeset Swasthik:2
create table if not exists contact_table (
    id UUID primary key,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    mobile varchar(20),
    deleted boolean default false,
    user_id UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_table (id)
);

INSERT INTO contact_table (id, first_name, last_name, email, mobile)
VALUES
    (UUID_GENERATE_V4(), 'John', 'Doe', 'johndoe@example.com', '9876543210'),
    (UUID_GENERATE_V4(), 'Jane', 'Smith', 'janesmith@example.com', '8765432109'),
    (UUID_GENERATE_V4(), 'Alice', 'Wonderland', 'alice@wonderland.com', '7654321098'),
    (UUID_GENERATE_V4(), 'Bob', 'Marley', 'bobmarley@example.com', '6543210987'),
    (UUID_GENERATE_V4(), 'Charlie', 'Brown', 'charliebrown@example.com', '5432109876'),
    (UUID_GENERATE_V4(), 'Emily', 'Clark', 'emilyclark@example.com', '1234567890'),
    (UUID_GENERATE_V4(), 'Michael', 'Jordan', 'mjordan@example.com', '2345678901'),
    (UUID_GENERATE_V4(), 'Sophia', 'Johnson', 'sophiaj@example.com', '3456789012'),
    (UUID_GENERATE_V4(), 'James', 'Bond', 'jamesbond@example.com', '4567890123'),
    (UUID_GENERATE_V4(), 'Olivia', 'Brown', 'oliviabrown@example.com', '5678901234'),
    (UUID_GENERATE_V4(), 'William', 'Smith', 'willsmith@example.com', '6789012345'),
    (UUID_GENERATE_V4(), 'Isabella', 'Davis', 'isabellad@example.com', '7890123456'),
    (UUID_GENERATE_V4(), 'Alexander', 'Wilson', 'alexwilson@example.com', '8901234567'),
    (UUID_GENERATE_V4(), 'Amelia', 'Moore', 'ameliam@example.com', '9012345678'),
    (UUID_GENERATE_V4(), 'Benjamin', 'Taylor', 'bentaylor@example.com', '0123456789'),
    (UUID_GENERATE_V4(), 'Mia', 'Anderson', 'miaanderson@example.com', '1123456789'),
    (UUID_GENERATE_V4(), 'Lucas', 'Thomas', 'lucast@example.com', '1223456789'),
    (UUID_GENERATE_V4(), 'Evelyn', 'Jackson', 'evelynj@example.com', '1323456789'),
    (UUID_GENERATE_V4(), 'Henry', 'White', 'henryw@example.com', '1423456789'),
    (UUID_GENERATE_V4(), 'Ava', 'Harris', 'avaharris@example.com', '1523456789'),
    (UUID_GENERATE_V4(), 'Daniel', 'Martin', 'danielm@example.com', '1623456789'),
    (UUID_GENERATE_V4(), 'Charlotte', 'Thompson', 'charlottet@example.com', '1723456789'),
    (UUID_GENERATE_V4(), 'Matthew', 'Garcia', 'mattgarcia@example.com', '1823456789'),
    (UUID_GENERATE_V4(), 'Harper', 'Martinez', 'harperm@example.com', '1923456789'),
    (UUID_GENERATE_V4(), 'Elijah', 'Robinson', 'elijahr@example.com', '2023456789');

