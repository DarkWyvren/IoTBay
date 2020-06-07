DROP TABLE STAFF;

CREATE TABLE STAFF
(
ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
Email varchar (128) UNIQUE,
FullName varchar (128),
Address varchar (255),
Pos varchar (128),
Status int not NULL,

PRIMARY KEY(ID)
);

INSERT INTO STAFF(Email, FullName,Address,Pos, Status) 
VALUES('pepe@gmail.com','Pai pei','123 Hujianyan St, HongDoui, Singapore','manager',1);

SELECT * FROM STAFF;
