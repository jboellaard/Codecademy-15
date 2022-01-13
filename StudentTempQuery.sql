USE master;
DROP DATABASE IF EXISTS StudentTemp;
CREATE DATABASE StudentTemp;
GO
USE StudentTemp;

 DROP TABLE IF EXISTS Address;
CREATE TABLE Address (
	AddressID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ZipCode varchar(64) NOT NULL,
	HouseNumber int NOT NULL, 
	Suffix char(1) NULL,
	Street varchar(64) NOT NULL,
	City varchar(64) NOT NULL,
	Country varchar(64) NOT NULL,
);

INSERT INTO Address
VALUES ('4813 HV', 28, NULL, 'Liesbospark', 'Breda', 'Nederland'),
 ('4611 GV', 66, NULL, 'Noordzijde haven', 'Bergen op Zoom', 'Nederland'),
 ('5045 DS', 51, NULL, 'Munnekeburenstraat', 'Tilburg', 'Nederland'),
 ('4611 JL', 38, NULL, 'Lievevrouwenstraat', 'Bergen op Zoom', 'Nederland'),
 ('4834 AT', 111, NULL, 'De blauwe kei', 'Breda', 'Nederland'),
 ('4611 GH', 7, 'b', 'Dubbelstraat', 'Bergen op Zoom', 'Nederland'),
 ('3701 JL', 23, NULL, 'Costerlaan', 'Zeist', 'Nederland');



DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
	EmailAddress varchar(64) NOT NULL PRIMARY KEY,
	Name varchar(64) NOT NULL,
	DateOfBirth varchar(64) NOT NULL,
	Gender char(1) NOT NULL,
	AddressID int NOT NULL FOREIGN KEY REFERENCES Address(AddressId),
);

INSERT INTO Student
VALUES ('marc0tjevp@gmail.com', 'Marco van Poortvliet', '1998-05-05', 'M',1),
 ('lisatyem@gmail.com', 'Lisa Tyem', '1998-10-18', 'F',2),
 ('renzoremmers@gmail.com', 'Renzo Remmers', '2000-07-21', 'M',3),
 ('rubenstrik@kpn.com', 'Ruben Strik', '2004-11-15', 'M',4),
 ('joeyletens@hotmail.com', 'Joey Letens', '2002-02-18', 'M',5),
 ('danirohder@kpn.com', 'Dani Rohder', '2001-08-09', 'M',6),
 ('johanneshoefman@hotmail.com', 'Johannes Hoefman', '1999-06-12', 'M',7);

 DROP TABLE IF EXISTS Course;
CREATE TABLE Course (
	CourseName varchar(64) NOT NULL PRIMARY KEY,
	Subject varchar(64) NOT NULL,
	IntroductionText varchar(64) NOT NULL,
	LevelIndication varchar(10) NOT NULL,
);

INSERT INTO Course
VALUES ('Web development', 'De basics', 'Bouw websites en web apps', 'Beginner'),
 ('Data science', 'Annalyseren', 'Vind logica in data', 'Beginner'),
 ('Computer Science', 'Annalyseren', 'Computer sciences, ook wel cs, is een breed bregrip', 'Advanced'),
 ('Machine learning', 'Data science', 'Machine learning is een opkomend onderdeel van data science', 'Advanced'),
 ('Web design', 'Opmaak en layout', 'Web design is verslavend als je eraan begint', 'Expert');