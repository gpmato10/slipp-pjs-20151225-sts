DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS(
	userid varchar(12) not null,
	password varchar(12) not null,
	name varchar(20) not null,
	email varchar(50),
	
	PRIMARY KEY (userid)
);

INSERT INTO USERS VALUES('javajigi', 'password', '자바지기', 'javajigi@slipp.net');