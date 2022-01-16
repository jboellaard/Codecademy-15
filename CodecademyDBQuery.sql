USE master;
DROP DATABASE IF EXISTS CodecademyData;
CREATE DATABASE CodecademyData;
GO
USE CodecademyData;

 DROP TABLE IF EXISTS Address;
CREATE TABLE Address (
	AddressID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ZipCode varchar(64) NOT NULL,
	HouseNumber int NOT NULL, 
	Suffix char(1) NULL,
	Street varchar(64) NOT NULL,
	City varchar(64) NOT NULL,
	Country varchar(64) NOT NULL,
	CONSTRAINT UA_Address UNIQUE (ZipCode, HouseNumber, Suffix)
);

INSERT INTO Address
VALUES ('4813 HV', 28, NULL, 'Liesbospark', 'Breda', 'Nederland'),
 ('4611 GV', 66, NULL, 'Noordzijde haven', 'Bergen op Zoom', 'Nederland'),
 ('5045 DS', 51, NULL, 'Munnekeburenstraat', 'Tilburg', 'Nederland'),
 ('4611 JL', 38, NULL, 'Lievevrouwenstraat', 'Bergen op Zoom', 'Nederland'),
 ('4834 AT', 111, NULL, 'De blauwe kei', 'Breda', 'Nederland'),
 ('4611 GH', 7, 'b', 'Dubbelstraat', 'Bergen op Zoom', 'Nederland'),
 ('3701 JL', 23, NULL, 'Costerlaan', 'Zeist', 'Nederland'),
 ('3354 AJ', 4, NULL, 'J.G. Suurhoffstraat', 'Papendrecht', 'Nederland');



DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
	EmailAddress varchar(64) NOT NULL PRIMARY KEY,
	Name varchar(64) NOT NULL,
	DateOfBirth varchar(64) NOT NULL,
	Gender char(1) NOT NULL,
	AddressID int NOT NULL FOREIGN KEY REFERENCES Address(AddressId)
);

INSERT INTO Student
VALUES ('marc0tjevp@gmail.com', 'Marco van Poortvliet', '1998-05-05', 'M',1),
 ('lisatyem@gmail.com', 'Lisa Tyem', '1998-10-18', 'F',2),
 ('renzoremmers@gmail.com', 'Renzo Remmers', '2000-07-21', 'M',3),
 ('rubenstrik@kpn.com', 'Ruben Strik', '2004-11-15', 'M',4),
 ('joeyletens@hotmail.com', 'Joey Letens', '2002-02-18', 'M',5),
 ('danirohder@kpn.com', 'Dani Rohder', '2001-08-09', 'M',6),
 ('johanneshoefman@hotmail.com', 'Johannes Hoefman', '1999-06-12', 'M',7),
 ('joy.boe@gmail.com', 'Joy Boellaard', '1998-11-11', 'F',8);

 DROP TABLE IF EXISTS Course;
CREATE TABLE Course (
	CourseName varchar(64) NOT NULL PRIMARY KEY,
	Subject varchar(64) NOT NULL,
	IntroductionText varchar(64) NOT NULL,
	LevelIndication varchar(10) NOT NULL
);

INSERT INTO Course
VALUES ('Web development', 'De basics', 'Bouw websites en web apps', 'Beginner'),
 ('Data science', 'Analyseren', 'Vind logica in data', 'Beginner'),
 ('Computer Science', 'Analyseren', 'Computer sciences, ook wel cs, is een breed bregrip', 'Advanced'),
 ('Machine learning', 'Data science', 'Machine learning is een opkomend onderdeel van data science', 'Advanced'),
 ('Web design', 'Opmaak en layout', 'Web design is verslavend als je eraan begint', 'Expert');

  DROP TABLE IF EXISTS RecommendedCourse;
CREATE TABLE RecommendedCourse (
	CourseName varchar(64) NOT NULL FOREIGN KEY REFERENCES Course(CourseName),
	RecommendedCourseName varchar(64) NOT NULL FOREIGN KEY REFERENCES Course(CourseName),
	CONSTRAINT PK_RecommendedCourse PRIMARY KEY (CourseName, RecommendedCourseName)
);

--INSERT INTO RecommendedCourse
--VALUES ();

DROP TABLE IF EXISTS Certificate;
CREATE TABLE Certificate (
	CertificateID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	--EnrollmentID int NOT NULL FOREIGN KEY REFERENCES Enrollment(EnrollmentID),
	Grade decimal NOT NULL,
	NameStaffCodecademy varchar(64) NOT NULL
);

INSERT INTO Certificate
VALUES ( 8, 'Harrie van Tilburg'),
( 7, 'Sam van der Flaas'),
( 5, 'Sam van der Flaas'),
(10, 'Karel Hasselt'),
( 6, 'Felix Martens'),
( 9, 'Karel Hasselt'),
( 8.5, 'Harrie van Tilburg'); 


DROP TABLE IF EXISTS Enrollment;
CREATE TABLE Enrollment (
	EnrollmentID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	StudentEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Student(EmailAddress),
	CourseName varchar(64) NOT NULL FOREIGN KEY REFERENCES Course(CourseName),
	SignUpDate varchar(64) NOT NULL,
	--CertificateID int NULL FOREIGN KEY REFERENCES Certificate(CertificateID),
	Certificate int NOT NULL,
	CONSTRAINT UK_Enrollment UNIQUE (StudentEmail, CourseName, SignUpDate)
);

INSERT INTO Enrollment
VALUES ('marc0tjevp@gmail.com', 'Machine learning', '2021-01-15', 0),
('marc0tjevp@gmail.com', 'Computer science', '2021-01-20', 0),
('marc0tjevp@gmail.com', 'Data science', '2021-01-23', 1),
('lisatyem@gmail.com', 'Web development', '2021-01-07', 0),
('renzoremmers@gmail.com', 'Web development', '2021-01-10', 1),
('renzoremmers@gmail.com', 'Machine learning', '2021-01-01', 0),
('rubenstrik@kpn.com', 'Data science', '2021-01-19', 1),
('joeyletens@hotmail.com', 'Data science', '2021-01-03', 0),
('joeyletens@hotmail.com', 'Computer science', '2021-01-18', 0),
('danirohder@kpn.com', 'Web development', '2021-01-09', 1),
('danirohder@kpn.com', 'Machine learning', '2021-01-16', 0),
('johanneshoefman@hotmail.com', 'Data science', '2021-01-06', 0),
('johanneshoefman@hotmail.com', 'Computer science', '2021-01-14',1),
('johanneshoefman@hotmail.com', 'Web development', '2021-01-18', 1),
('joy.boe@gmail.com', 'Computer science', '2021-10-15', 0),
('joy.boe@gmail.com', 'Machine learning', '2021-12-01', 0),
('joy.boe@gmail.com', 'Data science', '2022-01-06', 1);

DROP TABLE IF EXISTS ContentItem;
CREATE TABLE ContentItem (
	ContentItemID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	PublicationDate varchar(64) NOT NULL,
	Status varchar(10) NOT NULL
);

INSERT INTO ContentItem
VALUES ('2019-10-15', 'Archived'),
 ('2019-10-20', 'Active'),
 ('2019-10-29', 'Active'),
 ('2019-11-07', 'Concept'),
 ('2020-01-13', 'Archived'),
 ('2019-10-15', 'Archived'),
 ('2019-10-20', 'Active'),
 ('2019-10-29', 'Active'),
 ('2019-11-07', 'Concept'),
 ('2020-01-13', 'Archived'),
 ('2019-10-15', 'Archived'),
 ('2019-10-20', 'Active'),
 ('2019-10-29', 'Active'),
 ('2019-11-07', 'Active'),
 ('2020-01-13', 'Archived');


DROP TABLE IF EXISTS Module;
CREATE TABLE Module (
	ContentItemID int NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES ContentItem(ContentItemID),
	Title varchar(64) NOT NULL,
	Version varchar(10) NOT NULL,
	Description varchar(64) NULL,
	NameContactPerson varchar(64) NOT NULL,
	EmailContactPerson varchar(64) NOT NULL,
	FollowNumber int NOT NULL,
	CourseName varchar(64) NULL FOREIGN KEY REFERENCES Course(CourseName),
	CONSTRAINT CU_Module UNIQUE (Title, Version)
);

INSERT INTO Module
VALUES (1,'Leer HTML', 'v11', 'HTML is de basis van alle web paginas', 'Sauter de Vis', 'sauterdevis@outlook.com', 1, 'Web development'),
 (2,'Leer JavaScript', 'v12', 'JavaScript is een van de meest krachtige programmeer talen', 'Sauter de Vis', 'sauterdevis@outlook.com', 12, 'Web development'),
 (3,'Leer R', 'v21', 'R is een veelgebruikt statistische programmeertaal', 'Mark de Rond', 'markderond@outlook.com', 21, 'Data science'),
 (4,'Leer python 2', 'v22', 'Python is een veelzijdige en populaire programmeertaal', 'Mark de Rond', 'markderond@outlook.com', 2, NULL),
 (5,'Leer C++', 'v31', 'C++ wordt geprezen voor zijn laagdrempelige functionaliteit', 'Ralph van Venrooij', 'ralphvanvenrooij@outlook.com', 1, 'Computer science' ),
 (6,'Leer C#', 'v32', 'C# opent veel deuren voor je als programmeur', 'Ralph van Venrooij', 'ralphvanvenrooij@outlook.com', 32, 'Computer science'),
 (7,'Leer Alexa programmeren', 'v41', 'Leer zo je eigen voice user interface te bouwen', 'Klaas van Zundert', 'klaasvanzundert@outlook.com', 4, 'Machine learning'),
 (8,'Leer de Watson API', 'v42', 'IBM Watson is een van de meest sterke APIs ter wereld', 'Klaas van Zundert', 'klaasvanzundert@outlook.com', 42, NULL );


DROP TABLE IF EXISTS Webcast;
CREATE TABLE Webcast (
	ContentItemID int NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES ContentItem(ContentItemID),
	Title varchar(64) NOT NULL UNIQUE,
	Description varchar(128) NULL,
	DurationInSeconds int NULL,
	URL varchar(128) NULL,
	NameSpeaker varchar(64) NULL,
	NameOrganisation varchar(64) NULL
);


INSERT INTO Webcast
VALUES (9,'Maak je eerste HTML/CSS/JS project', 'Leer de basis voor websites bouwen met JavaScript.', 21, 'https://www.youtube.com/watch?v=iwNUJU5D3aI&t=18s', 'Brandon Nazgull', 'Codecademy'),
 (10,'Wat is data sciences', 'Wil je weten wat data sciences is? Kijk dan dit interview.', 4, 'https://news.codecademy.com/what-is-data-science/', 'Sophie van Laarhoven', 'Codecademy'),
 (11,'Introductie in computer sciences', 'Neem een kijkje in het computer science pad van Codecademy', 1, 'https://www.codecademy.com/paths/computer-science/tracks/cspath-intro/modules/cspath-python-syntax', 'Nick Grayham', 'Codecademy'),
 (12,'Wat zijn neural networks?', 'Hoe kan een computer problemen oplossen zoals ons brein dat doet?', 15, 'https://news.codecademy.com/what-are-neural-networks/', 'Finnick Odair', 'Codecademy');


DROP TABLE IF EXISTS ProgressWebcast;
CREATE TABLE ProgressWebcast (
	ContentItemID int NOT NULL FOREIGN KEY REFERENCES ContentItem(ContentItemID),
	StudentEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Student(EmailAddress),
	Progress int NOT NULL DEFAULT 0,
	CONSTRAINT PK_ProgressWebcast PRIMARY KEY (ContentItemID,StudentEmail)
);



DROP TABLE IF EXISTS ProgressModule;
CREATE TABLE ProgressModule (
	ContentItemID int NOT NULL FOREIGN KEY REFERENCES ContentItem(ContentItemID),
	EnrollmentID int NOT NULL FOREIGN KEY REFERENCES Enrollment(EnrollmentID),
	Progress int NOT NULL DEFAULT 0,
	CONSTRAINT PK_ProgressModule PRIMARY KEY (ContentItemID,EnrollmentID)
);

INSERT INTO ProgressModule
VALUES (2,4,50);

INSERT INTO ProgressWebcast
VALUES (9,'renzoremmers@gmail.com',5),
(9,'marc0tjevp@gmail.com',95),
(10,'renzoremmers@gmail.com',5),
(11,'marc0tjevp@gmail.com',5);



/*

DROP TABLE IF EXISTS Bekeken;
CREATE TABLE Bekeken (
	ID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	ContentId int NOT NULL,
--	CursistEmail varchar(64) NOT NULL FOREIGN KEY REFERENCES Student(Email) ON DELETE CASCADE,
	Voortgang int NOT NULL DEFAULT '0',
--	CONSTRAINT UC_Bekeken UNIQUE (ContentId, CursistEmail)
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
 ('1', 'johanneshoefman@hotmail.com', 75),
 ('2', 'joy.boe@gmail.com', 45),
 ('4', 'joy.boe@gmail.com', 99);
*/






