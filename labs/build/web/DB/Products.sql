/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  antho
 * Created: 04/06/2020
 */
DROP TABLE PRODUCTDB;

CREATE TABLE PRODUCTDB (
    Product_ID int NOT NULL, 
    ProductName VARCHAR(100) NOT NULL unique,
    Price double NOT NULL unique,
    Category VARCHAR(100) NOT NULL,
    SupplierID int NOT NULL,

    PRIMARY KEY(Product_ID),
    FOREIGN KEY(SupplierID) REFERENCES SUPPLIERDB(SupplierID)

);

INSERT INTO PRODUCTDB(Product_ID, ProductName, Price, Category, SupplierID)
Values
(123456, 'Rasberry Pi', 10, 'Complete', 123512);
(1, 'IoTBay Device 1', 19.9, 'Electronic Device', 1);
(2, 'IoTBay Device 2', 29.9, 'Electronic Device', 2);
(3, 'IoTBay Device 3', 39.9, 'Electronic Device', 3);


select * from PRODUCTDB;

