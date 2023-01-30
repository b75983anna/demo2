CREATE SEQUENCE IF NOT EXISTS seq;

DROP TABLE IF EXISTS books;

CREATE TABLE books (

id LONG NOT NULL DEFAULT nextval('seq') PRIMARY KEY,
code INT NOT NULL,
title VARCHAR(200) NOT NULL,
author VARCHAR(200) NOT NULL,
added DATE NOT NULL,
read BIT(1) NOT NULL

);

insert into books (id, code, title, author, added, read)
values (nextval('seq'), 001, 'It ends with us', 'Colleen Hoover', '2022-12-13', 1),
        (nextval('seq'), 002, 'Atomic habits', 'James Clear', '2022-12-14', 1),
        (nextval('seq'), 003, 'The Very Hungry Caterpillar', 'Eric Carle', '2022-12-15', 1),
         (nextval('seq'), 004, 'Things We Never Got Over', 'Lucy Score', '2022-12-16', 0),
         (nextval('seq'), 005, 'You re My Little Cuddle Bug', 'Nicola Edwards', '2022-12-17', 1);
