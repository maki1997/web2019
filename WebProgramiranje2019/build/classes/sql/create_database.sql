DROP SCHEMA IF EXISTS web2019;
CREATE SCHEMA web2019 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE web2019;

CREATE TABLE users (
	id BIGINT AUTO_INCREMENT,
	userName VARCHAR(15) NOT NULL, 
	password VARCHAR(15) NOT NULL,
	registrationDate date, 
	role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER', 
	blocked boolean,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id)
);

CREATE TABLE flights (
	id BIGINT AUTO_INCREMENT,
	flightNumber VARCHAR(15) NOT NULL, 
	startDate date, 
	endDate date,
	startAirport INT NOT NULL,
	endAirport INT NOT NULL,
	numberOfSeats INT NOT NULL,
	ticketPrice double NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id)
);

CREATE TABLE airports (
	id BIGINT AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL, 
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id)
);

CREATE TABLE reservations (
	id BIGINT AUTO_INCREMENT,
	startFlight INT NOT NULL,
	endFlight INT NOT NULL,
	startFlightSeat INT NOT NULL,
	endFlightSeat INT NOT NULL,
	reservationDate DATE NOT NULL,
	ticketSaleDate DATE NOT NULL,
	passenger INT NOT NULL,
	passengerFirstName VARCHAR(20) NOT NULL,
	passengerLastName VARCHAR(20) NOT NULL,
	deleted BOOLEAN DEFAULT 0 NOT NULL ,
	PRIMARY KEY(id)
);



	INSERT INTO users(userName,password,registrationDate,role,blocked,deleted) VALUES('admin','admin','2018-7-3','ADMIN',false,false);
	INSERT INTO users(userName,password,registrationDate,role,blocked,deleted) VALUES('user1','user1','2018-2-3','USER',false,false);
	INSERT INTO users(userName,password,registrationDate,role,blocked,deleted) VALUES('user2','user2','2018-4-3','USER',false,false);
    INSERT INTO users(userName,password,registrationDate,role,blocked,deleted) VALUES('user3','user3','2018-6-12','USER',false,false);
	
	INSERT INTO flights(flightNumber, startDate, endDate, startAirport, endAirport, numberOfSeats, ticketPrice, deleted) VALUES('123', '2018-7-3', '2018-8-4', 1, 3, 50, 5000.00, false);
	INSERT INTO flights(flightNumber, startDate, endDate, startAirport, endAirport, numberOfSeats, ticketPrice, deleted) VALUES('223', '2018-7-4', '2018-8-5', 2, 3, 50, 8000.00, false);
	INSERT INTO flights(flightNumber, startDate, endDate, startAirport, endAirport, numberOfSeats, ticketPrice, deleted) VALUES('444', '2018-7-5', '2018-8-6', 4, 1, 50, 7000.00, false);
	INSERT INTO flights(flightNumber, startDate, endDate, startAirport, endAirport, numberOfSeats, ticketPrice, deleted) VALUES('555', '2018-7-6', '2018-8-1', 2, 4, 50, 9000.00, false);
	
	INSERT INTO airports(name,deleted) VALUES('Beograd', false);
	INSERT INTO airports(name,deleted) VALUES('London', false);
	INSERT INTO airports(name,deleted) VALUES('Tokyo', false);
	INSERT INTO airports(name,deleted) VALUES('New York', false);
	
	insert into reservations (startFlight,endFlight,startFlightSeat,endFlightSeat,reservationDate,ticketSaleDate,passenger,passengerFirstName,passengerLastName,deleted) values (1,3,3,3,'2018-7-3','2018-9-3',1,'NIko','Bitan',false);
	
	