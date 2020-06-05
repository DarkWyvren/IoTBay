/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  antho
 * Created: 04/06/2020
 */

CREATE TABLE PRODUCTDB (
    Product_ID int NOT NULL, 
    ProductName VARCHAR(100) NOT NULL,
    Price double NOT NULL,
    Category VARCHAR(100) NOT NULL,
    SupplierID (100) NOT NULL,
    PRIMARY KEY(Product_ID)
)
