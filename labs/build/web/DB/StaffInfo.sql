


CREATE TABLE STAFF
(
ID int not NULL,
Email varchar (128) UNIQUE,
Password varchar (128) NOT NULL,
FullName varchar (128),
DOB DATE,
Address varchar (255),
Phone int,
Position varchar (128),

PRIMARY KEY(ID)
);
