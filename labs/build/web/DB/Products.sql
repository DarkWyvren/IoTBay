/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  antho
 * Created: 04/06/2020
 */
/*DROP TABLE PRODUCTDB;
*/
DROP TABLE PRODUCTDB;

DROP TABLE SUPPLIERDB;

CREATE TABLE SUPPLIERDB (
    SupplierID int NOT NULL AUTO_INCREMENT,
    SupName varchar(128) NOT NULL,
    SupAddress varchar(128) NOT NULL,
    SupType varchar(128),
    SupEmail varchar(128),
    SupStatus int,
    PRIMARY KEY(SupplierID)
);

CREATE TABLE PRODUCTDB (
    ProductID int NOT NULL AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    ProductPrice double NOT NULL,
    Category VARCHAR(100) NOT NULL,
    SupplierID int NOT NULL,
    Quantity int NOT NULL,

    PRIMARY KEY (ProductID),
    FOREIGN KEY(SupplierID) REFERENCES SUPPLIERDB(SupplierID)
);


insert into SUPPLIERDB(SupName, SupAddress,SupType, SupEmail, SupStatus)
Values 
('UNIBITZ', 'U13 Featherweight Avenue, Springfield, NSW, 2630', 'RAM', 'unibitzservice@gmail.com', 1),
('Trial1', 'U17 Featherweight Avenue, Springfield, NSW, 2630', 'RAM', 'trial1@trial1.com',0),
('IOTFOR3', '23 Ghethorst Avenue, Springfield, NSW, 2630', 'System', 'iot43@gmail.com', 1),
('DONKYE', '47 Bromstain St, Springfield, NSW, 2630', 'CPU', 'donkye@trial1.com',0),
('DEPHI', 'U18 Feaht Street, Jenkins, NSW, 2888', 'Case', 'dephi@trial1.com',0);

INSERT INTO PRODUCTDB (ProductName, ProductPrice, Category, SupplierID, Quantity)
VALUES
('Rasberry Pi', 100, 'Computers', 2, 1000),
('Rasberry Pi 2', 150, 'Computers', 2, 600),
('Arduino', 80.50, 'Computers', 1, 100),
('Learn c++ in 20 days', 25, 'Books', 1, 200),
('Java 8 Ultimate refernce suite', 30, 'Books', 1, 50),
('Bluetooth optical mouse', 15, 'Accessories', 4, 50),
('USBC Cable', 1, 'Cables', 3, 2000),
('USBB Cable', 1.5, 'Cables', 4, 2000),
('MicroUSB Cable', 1.2, 'Cables', 4, 1000),
('USBA Cable', 0.8, 'Cables', 3, 3000), 

('Arduino Nano', 70, 'Computers', 2, 100),
('Xeron V', 50, 'Cases', 2, 600),
('DeltaMax Slim', 30.50, 'Cases', 1, 100),
('Why programming is a mistake', 25, 'Books', 1, 200),
('14 reasons why MVC is outdated', 30, 'Books', 1, 50),
('Bluetooth Camera', 25, 'Accessories', 4, 50),
('Debugging duck', 1, 'Accessories', 3, 2000),
('Mechanical Keyboard', 45, 'Accessories', 4, 200),
('Furry poster', 10, 'Accessories', 4, 100000),
('USBX Cable', 4.5, 'Cables', 3, 3)


;


select * from PRODUCTDB;

