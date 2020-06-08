DROP TABLE STAFF_SESSION;
DROP TABLE STAFF;

CREATE TABLE STAFF
(
    ID int NOT NULL AUTO_INCREMENT,
    Email varchar (128) UNIQUE,
    Password varchar (128) NOT NULL,
    FullName varchar (128),
  
    Address varchar (255),
    Pos varchar (128),
    Status int,

    PRIMARY KEY(ID)
    );

    INSERT INTO STAFF(Email, Password,FullName,Address,Pos, Status) 
    VALUES('pepestaff@gmail.com','password','Big Pai pei','123 Hujianyan St, HongDoui, Singapore','manager',1);

SELECT * FROM STAFF;



CREATE TABLE STAFF_SESSION (
    Staff_ID int NOT NULL,
    LOGGEDIN_DATE DATE NOT NULL,
    LOGGEDIN_TIME TIME NOT NULL,
    LOGGEDOUT_DATE DATE,
    LOGGEDOUT_TIME TIME,

    
    FOREIGN KEY(Staff_ID) REFERENCES STAFF(ID),
    CONSTRAINT PK_S_SESSION PRIMARY KEY(Staff_ID,LOGGEDIN_DATE,LOGGEDIN_TIME)

);