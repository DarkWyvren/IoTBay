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

CREATE TABLE PRODUCTDB (
    ProductID int GENERATED ALWAYS AS IDENTITY NOT NULL, 
    ProductName VARCHAR(100) NOT NULL,
    ProductPrice double NOT NULL,
    Category VARCHAR(100) NOT NULL,
    SupplierID int NOT NULL,
    Quantity int NOT NULL,

    PRIMARY KEY (ProductID)

);

INSERT INTO PRODUCTDB (ProductName, ProductPrice, Category, SupplierID, Quantity)
Values
('Rasberry Pi', 10, 'Complete', 123452, 1000);


select * from PRODUCTDB;

