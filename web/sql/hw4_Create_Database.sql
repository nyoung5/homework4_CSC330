/*
@author Nathan Young and Harrison Durant
*/

DROP DATABASE IF EXISTS library;
CREATE DATABASE library;

CREATE TABLE library.book
(
book_name varchar(50) NOT NULL PRIMARY KEY
);

CREATE TABLE library.user
(
first_name varchar(50) NOT NULL,
last_name varchar(50) NOT NULL,
email varchar(50) NOT NULL PRIMARY KEY
);

CREATE TABLE library.checkout
(
checkout_date date NOT NULL,
due_date date NOT NULL,
user_email varchar(50),
book_name varchar(50),
FOREIGN KEY(user_email) REFERENCES user(email),
FOREIGN KEY(book_name) REFERENCES book(book_name)
);