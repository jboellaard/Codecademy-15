USE master;
DROP DATABASE IF EXISTS CodecademyStatistics;
CREATE DATABASE CodecademyStatistics;
GO
USE CodecademyStatistics;

DROP TABLE IF EXISTS Cursus;
CREATE TABLE Cursus (
	Naam varchar(64) NOT NULL PRIMARY KEY,
	Onderwerp varchar(64) NOT NULL,
	IntroductieTekst varchar(128) NOT NULL,
	NiveauAanduiding char(1) NOT NULL,
	CursusAanbeveling varchar(128) NOT NULL
);

INSERT INTO Cursus
VALUES ('Web development', 'De basics', 'Bouw websites en web apps', 1, 'Computer science'),
 ('Data science', 'Annalyseren', 'Vind logica in data', 2, 'Machine learning'),
 ('Computer Science', 'Annalyseren', 'Computer sciences, ook wel cs, is een breed bregrip', 3, 'Data science'),
 ('Machine learning', 'Data science', 'Machine learning is een opkomend onderdeel van data science', 4, 'Web development'),
 ('Web design', 'Opmaak en layout', 'Web design is verslavend als je eraan begint', 5, 'Web development');



DROP TABLE IF EXISTS Module;
CREATE TABLE Module (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY ,
	Titel varchar(64) NOT NULL,
	Versie varchar(32) NOT NULL,
	Beschrijving varchar(128) NOT NULL,
	ContactPersoon varchar(64) NOT NULL,
	EmailContactPersoon varchar(64) NOT NULL,
	Volgnummer int NOT NULL,
	CursusNaam varchar(64) NOT NULL FOREIGN KEY REFERENCES Cursus(Naam),
	CONSTRAINT UC_Module UNIQUE (Titel, Versie)
);

INSERT INTO Module
VALUES ('Leer HTML', 'v11', 'HTML is de basis van alle web paginas', 'Sauter de Vis', 'sauterdevis@outlook.com', 1.1, 'Web development'),
 ('Leer JavaScript', 'v12', 'JavaScript is een van de meest krachtige programmeer talen', 'Sauter de Vis', 'sauterdevis@outlook.com', 1.2, 'Web development'),
 ('Leer R', 'v21', 'R is een veelgebruikt statistische programmeertaal', 'Mark de Rond', 'markderond@outlook.com', 2.1, 'Data science'),
 ('Leer python 2', 'v22', 'Python is een veelzijdige en populaire programmeertaal', 'Mark de Rond', 'markderond@outlook.com', 2.2, 'Data science'),
 ('Leer C++', 'v31', 'C++ wordt geprezen voor zijn laagdrempelige functionaliteit', 'Ralph van Venrooij', 'ralphvanvenrooij@outlook.com', 3.1, 'Computer science' ),
 ('Leer C#', 'v32', 'C# opent veel deuren voor je als programmeur', 'Ralph van Venrooij', 'ralphvanvenrooij@outlook.com', 3.2, 'Computer science'),
 ('Leer Alexa programmeren', 'v41', 'Leer zo je eigen voice user interface te bouwen', 'Klaas van Zundert', 'klaasvanzundert@outlook.com', 4.1, 'Machine learning'),
 ('Leer de Watson API', 'v42', 'IBM Watson is een van de meest sterke APIs ter wereld', 'Klaas van Zundert', 'klaasvanzundert@outlook.com', 4.2, 'Machine learning' );



DROP TABLE IF EXISTS Webcast;
CREATE TABLE Webcast (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	Titel varchar(64) NOT NULL UNIQUE,
	Beschrijving varchar(128) NOT NULL,
	Tijdsduur int NOT NULL,
	Url varchar(128) NOT NULL,
	NaamSpreker varchar(64),
	OrganisatieSpreker varchar(64)
);

INSERT INTO Webcast
VALUES ('Maak je eerste HTML/CSS/JS project', 'Leer de basis voor websites bouwen met JavaScript.', 21, 'https://www.youtube.com/watch?v=iwNUJU5D3aI&t=18s', 'Brandon Nazgull', 'Codecademy'),
 ('Wat is data sciences', 'Wil je weten wat data sciences is? Kiujk dan dit interview.', 4, 'https://news.codecademy.com/what-is-data-science/', 'Sophie van Laarhoven', 'Codecademy'),
 ('Introductie in computer sciences', 'Neem een kijkje in het computer science pad van Codecademy', 1, 'https://www.codecademy.com/paths/computer-science/tracks/cspath-intro/modules/cspath-python-syntax', 'Nick Grayham', 'Codecademy'),
 ('Wat zijn neural networks?', 'Hoe kan een computer problemen oplossen zoals ons brein dat doet?', 15, 'https://news.codecademy.com/what-are-neural-networks/', 'Finnick Odair', 'Codecademy');



DROP TABLE IF EXISTS ContentItem;
CREATE TABLE ContentItem (
	ContentId int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	PublicatieDatum date NOT NULL,
	Status char(1) NOT NULL,
	ModuleID int FOREIGN KEY REFERENCES Module(ID),
	WebcastID int FOREIGN KEY REFERENCES Webcast(ID)
);

INSERT INTO ContentItem
VALUES ('2019-10-15', '0', null, 1),
 ('2019-10-20', '1', null, 1),
 ('2019-10-29', '2', 1, null),
 ('2019-11-07', '2', 2, null),
 ('2020-01-13', '1', null, 3);



DROP TABLE IF EXISTS Cursist;
CREATE TABLE Cursist (
	Email varchar(64) NOT NULL PRIMARY KEY,
	Naam varchar(64) NOT NULL,
	GeboorteDatum date NOT NULL,
	Geslacht char(1) NOT NULL,
	Adres varchar(64) NOT NULL,
	Woonplaats varchar(32) NOT NULL,
	Land varchar(32) NOT NULL
);

INSERT INTO Cursist
VALUES ('marc0tjevp@gmail.com', 'Marco van Poortvliet', '1998-05-05', '0', 'Liesbospark 28', 'Breda', 'Nederland'),
 ('lisatyem@gmail.com', 'Lisa Tyem', '1998-10-18', '1', 'Noordzijde haven 66', 'Bergen op Zoom', 'Nederland'),
 ('renzoremmers@gmail.com', 'Renzo Remmers', '2000-07-21', '1', 'Munnekeburenstraat 51', 'Tilburg', 'Nederland'),
 ('rubenstrik@kpn.com', 'Ruben Strik', '2004-11-15', '1', 'Lievevrouwenstraat 38', 'Rijen', 'Nederland'),
 ('joeyletens@hotmail.com', 'Joey Letens', '2002-02-18', '1', 'De blauwe kei 111', 'Breda', 'Nederland'),
 ('danirohder@kpn.com', 'Dani Rohder', '2001-08-09', '1', 'Dubbelstraat 7b', 'Oosterhout', 'Nederland'),
 ('johanneshoefman@hotmail.com', 'Johannes Hoefman', '1999-06-12', '0', 'Costerlaan 23', 'Zeist', 'Nederland');



DROP TABLE IF EXISTS Bekeken;
CREATE TABLE Bekeken (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ContentId int NOT NULL,
	CursistEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Cursist(Email) ON DELETE CASCADE,
	Voortgang int NOT NULL DEFAULT '0',
	CONSTRAINT UC_Bekeken UNIQUE (ContentId, CursistEmail)
);

INSERT INTO Bekeken
VALUES ('4', 'marc0tjevp@gmail.com', 0),
 ('3', 'marc0tjevp@gmail.com', 80),
 ('2', 'marc0tjevp@gmail.com', 100),
 ('1', 'lisatyem@gmail.com', 0),
 ('1', 'renzoremmers@gmail.com', 20),
 ('4', 'renzoremmers@gmail.com', 35),
 ('2', 'rubenstrik@kpn.com', 60),
 ('2', 'joeyletens@hotmail.com', 25),
 ('5', 'joeyletens@hotmail.com', 40),
 ('1', 'danirohder@kpn.com', 20),
 ('4', 'danirohder@kpn.com', 18),
 ('2', 'johanneshoefman@hotmail.com', 100),
 ('3', 'johanneshoefman@hotmail.com', 60),
 ('1', 'johanneshoefman@hotmail.com', 75);



DROP TABLE IF EXISTS Certificaat;
CREATE TABLE Certificaat (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	CursistEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Cursist(Email) ON DELETE CASCADE,
	CursusNaam varchar(64) NOT NULL FOREIGN KEY REFERENCES Cursus(Naam),
	Beoordeling int NOT NULL,
	BaanMedewerker varchar(32)
);

INSERT INTO Certificaat
VALUES ('marc0tjevp@gmail.com', 'Data science', 8, 'Harrie van Tilburg'),
('renzoremmers@gmail.com', 'Data science', 7, 'Sam van der Flaas'),
('rubenstrik@kpn.com', 'Machine learning', 5, 'Sam van der Flaas'),
('danirohder@kpn.com', 'Web design', 10, 'Karel Hasselt'),
('johanneshoefman@hotmail.com', 'Web design', 6, 'Felix Martens'),
('johanneshoefman@hotmail.com', 'Web development', 9, 'Karel Hasselt');



DROP TABLE IF EXISTS Inschrijving;
CREATE TABLE Inschrijving (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	CursistEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Cursist(Email) ON DELETE CASCADE,
	CursusNaam varchar(64) NOT NULL,
	InschrijfDatum date NOT NULL,
	CertificaatId int FOREIGN KEY REFERENCES Certificaat(ID),
	Voortgang int NOT NULL DEFAULT '0',
	CONSTRAINT CU_Inschrijving UNIQUE (CursistEmail, CursusNaam, InschrijfDatum)
);

INSERT INTO Inschrijving
VALUES ('marc0tjevp@gmail.com', 'Machine learning', '2021-01-15', NULL, 50),
('marc0tjevp@gmail.com', 'Computer science', '2021-01-20', 2, 100),
('marc0tjevp@gmail.com', 'Data science', '2021-01-23', NULL, 66),
('lisatyem@gmail.com', 'Web development', '2021-01-07', NULL, 15),
('renzoremmers@gmail.com', 'Web development', '2021-01-10', NULL, 75),
('renzoremmers@gmail.com', 'Machine learning', '2021-01-01', 3, 100),
('rubenstrik@kpn.com', 'Data science', '2021-01-19', 4, 100),
('joeyletens@hotmail.com', 'Data science', '2021-01-03', NULL, 37),
('joeyletens@hotmail.com', 'Computer science', '2021-01-18', NULL, 5),
('danirohder@kpn.com', 'Web development', '2021-01-09', 5, 100),
('danirohder@kpn.com', 'Machine learning', '2021-01-16', NULL, 99),
('johanneshoefman@hotmail.com', 'Data science', '2021-01-06', 6, 100),
('johanneshoefman@hotmail.com', 'Computer science', '2021-01-14', 5, 100),
('johanneshoefman@hotmail.com', 'Web development', '2021-01-18', NULL, 25);