DROP SCHEMA IF EXISTS RazmenaVideoSadrzaja;
CREATE SCHEMA RazmenaVideoSadrzaja DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE RazmenaVideoSadrzaja;

CREATE TABLE users (
	id BIGINT AUTO_INCREMENT,
	userName VARCHAR(15) NOT NULL, 
	password VARCHAR(15) NOT NULL,
	name VARCHAR(15),
	surname VARCHAR(15),
	email VARCHAR(30) NOT NULL,
	description VARCHAR(150),
	dateReg date, 
	role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER', 
	blocked boolean,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id)
);
	INSERT INTO users(userName,password,name,surname,email,description,dateReg,role,blocked,deleted) VALUES('admin','admin','Marko','Filipovic','marko@gmail.com','description by admin','2018-7-3','ADMIN',false,false);
	INSERT INTO users(userName,password,name,surname,email,description,dateReg,role,blocked,deleted) VALUES('user1','user1','Nikola','Rajovic','nikola@gmail.com','description by user1','2018-2-3','USER',false,false);
	INSERT INTO users(userName,password,name,surname,email,description,dateReg,role,blocked,deleted) VALUES('user2','user2','Petar','Nikolic','petar@gmail.com','description by user2','2018-4-3','USER',false,false);
    INSERT INTO users(userName,password,name,surname,email,description,dateReg,role,blocked,deleted) VALUES('user3','user3','Jelena','Maksic','jelena@gmail.com','description by user3','2018-6-12','USER',false,false);
	
CREATE TABLE videos(
	id BIGINT AUTO_INCREMENT,
	url VARCHAR(50) NOT NULL,
	thumbnail VARCHAR(50) NOT NULL,
	name VARCHAR(100),
	description VARCHAR(150),
	visibility ENUM('PUBLIC', 'UNLISTED','PRIVATE') NOT NULL DEFAULT 'PUBLIC',
	blocked boolean,
	previews INT NOT NULL DEFAULT 1,
	dateUrl date, 
	owner BIGINT,
	likesVis boolean,
	allowRating BOOLEAN DEFAULT TRUE,
	allowComment BOOLEAN NOT NULL DEFAULT TRUE,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    likeNumber bigint(20) NOT NULL,
	dislikeNumber bigint(20) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (owner) references users(id) 
);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('sENM2wA_FTg','1','Imagine Dragons  It s Time','Description by User1','PUBLIC',false,10,'2018-2-5',2,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('uv_1AKKKJnk','1','Twenty one pilots Levitate ','Description by User1','PUBLIC',false,11,'2018-10-5',2,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('P8HzYZ74','1','Zedd, Alessia Cara  Stay','Description by User1','PUBLIC',false,12,'2018-3-10',2,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('fwGNCPCE5rw','1','Arensky x Adam Knight - Falling Dreamer','Description by Admin','PRIVATE',false,15,'2018-7-9',1,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('kXYiU_JCYtU','1','Numb (Official Video) - Linkin Park','Description by User2','PUBLIC',false,22,'2018-5-12',3,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('kPBzTxZQG5Q','1','3 Doors Down - Here Without You','Description by User3','PUBLIC',false,34,'2018-1-6',4,true,true,true,false,0,0);
INSERT INTO videos(url,thumbnail,name,description,visibility,blocked,previews,dateUrl,owner,likesVis,allowRating,allowComment,deleted,likeNumber,dislikeNumber) 
VALUES('n4RjJKxsamQ','1','Scorpions - Wind Of Change','Description by User3','UNLISTED',false,23,'2018-7-7',4,true,true,true,false,0,0);

CREATE TABLE comment (
	id BIGINT,
	content VARCHAR(200) NOT NULL,
	dateCom date,
	owner BIGINT,
	video BIGINT,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    likeNumber bigint(20) NOT NULL DEFAULT 0,
	dislikeNumber bigint(20) NOT NULL DEFAULT 0,
	PRIMARY KEY(id),
	FOREIGN KEY (owner) references users(id),
	FOREIGN KEY (video) references videos(id)
);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(1,'Comment in video 1 by user 1','2018-2-5',2,1,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(2,'Comment in video 1 by user 2','2018-3-6',3,1,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(3,'Comment in video 1 by user 3','2018-4-7',4,1,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(4,'Comment in video 2 by user 1','2018-10-5',2,2,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(5,'Comment in video 2 by user 2','2018-10-6',3,2,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(6,'Comment in video 2 by user 3','2018-10-7',4,2,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(7,'Comment in video 3 by user 1','2018-3-10',2,3,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(8,'Comment in video 3 by user 2','2018-4-10',3,3,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(9,'Comment in video 3 by user 3','2018-5-10',4,3,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(10,'Comment in video 4 by user 1','2018-7-9',2,4,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(11,'Comment in video 4 by user 2','2018-8-9',3,4,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(12,'Comment in video 4 by user 3','2018-9-9',4,4,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(14,'Comment in video 5 by user 1','2018-5-12',2,5,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(15,'Comment in video 5 by user 2','2018-6-12',3,5,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(16,'Comment in video 5 by user 3','2018-7-12',4,5,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(17,'Comment in video 6 by user 1','2018-1-6',2,6,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(18,'Comment in video 6 by user 2','2018-1-7',3,6,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(19,'Comment in video 6 by user 3','2018-1-9',4,6,false,0,0);

INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(20,'Comment in video 7 by user 1','2018-7-7',2,7,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(21,'Comment in video 7 by user 2','2018-8-7',3,7,false,0,0);
INSERT INTO comment(id,content,dateCom,owner,video,deleted,likeNumber,dislikeNumber) 
VALUES(22,'Comment in video 7 by user 3','2018-9-7',4,7,false,0,0);

CREATE TABLE subscribers (
	id BIGINT AUTO_INCREMENT,
	idUser BIGINT,
	subscriber BIGINT,
	
	PRIMARY KEY(id),
	FOREIGN KEY (idUser) references users(id),
	FOREIGN KEY (subscriber) references users(id)

);
CREATE TABLE likes (
	id BIGINT,
	likeDislike boolean,
	likeDate date, 
	owner BIGINT,
	PRIMARY KEY(id),
	FOREIGN KEY (owner) REFERENCES users(id) 
);

CREATE TABLE likeDislikeVideo(
likeId BIGINT,
videoId BIGINT,
FOREIGN KEY (likeId) REFERENCES likes (id) ,
FOREIGN KEY (videoId) REFERENCES videos(id) 
);

CREATE TABLE likeDislikeComment(
likeId BIGINT,
commentId BIGINT,
FOREIGN KEY (likeId) REFERENCES likes (id) ,
FOREIGN KEY (commentId) REFERENCES comment (id)
);