DROP DATABASE martial_arts_app;
CREATE DATABASE martial_arts_app;
USE martial_arts_app;

CREATE TABLE USER
(
	username VARCHAR(50) primary key,
	PASSWORD varchar(50),
	firstName varchar(50),
	lastName varchar(50)
);

CREATE TABLE Technique
(
	id INT(10) primary KEY NOT NULL auto_increment,
	username varchar(50),
	NAME varchar(50),
	TYPE varchar(50),
	description varchar(50)
);

CREATE TABLE Favorite
(
	techId INT(50),
	techUsername VARCHAR(50)
);

CREATE TABLE Message
(
	id INT(10) primary key auto_increment,
	username VARCHAR(50),
	datePosted DATEtime DEFAULT(now()),
	contents varchar(50),
	recipient varchar(50)
);