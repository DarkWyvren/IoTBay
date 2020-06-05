/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  willi
 * Created: 03/06/2020
 */
CREATE TABLE CUSTOMERDB (
    Customer_ID int NOT NULL,
    Email varchar(128) UNIQUE,
    Password varchar(128) NOT NULL,
    FullName varchar(128),
    DOB DATE,
    Address varchar(255),
    Phone int,
    Title varchar(8),
    
    PRIMARY KEY(Customer_ID)
    

);

CREATE TABLE CUSTOMER_SESSION (
    Customer_ID int NOT NULL,
    LOGGEDIN_DATE DATE NOT NULL,
    LOGGEDIN_TIME TIME NOT NULL,
    LOGGEDOUT_DATE DATE,
    LOGGEDOUT_TIME TIME,

    
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    CONSTRAINT PK_C_SESSION PRIMARY KEY(Customer_ID,LOGGEDIN_DATE,LOGGEDIN_TIME)

);


INSERT INTO CUSTOMERDB 
VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');


