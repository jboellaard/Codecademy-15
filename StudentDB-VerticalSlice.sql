USE master;
DROP DATABASE IF EXISTS CodecademyStatistics;
CREATE DATABASE CodecademyStatistics;
GO
USE CodecademyStatistics;

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
	DateOfBirth date NOT NULL,
	Gender char(1) NOT NULL,
	AddressID int NOT NULL FOREIGN KEY REFERENCES Address(AddressId),
);

INSERT INTO Student
VALUES ('marc0tjevp@gmail.com', 'Marco van Poortvliet', '1998-05-05', 'M'),
 ('lisatyem@gmail.com', 'Lisa Tyem', '1998-10-18', 'F'),
 ('renzoremmers@gmail.com', 'Renzo Remmers', '2000-07-21', 'M'),
 ('rubenstrik@kpn.com', 'Ruben Strik', '2004-11-15', 'M'),
 ('joeyletens@hotmail.com', 'Joey Letens', '2002-02-18', 'M'),
 ('danirohder@kpn.com', 'Dani Rohder', '2001-08-09', 'M'),
 ('johanneshoefman@hotmail.com', 'Johannes Hoefman', '1999-06-12', 'M');
