/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mood35-Laptop
 */

CREATE TABLE SUPPLIERDB (
    SupplierID int NOT NULL,
    ContactName varchar(128) NOT NULL,
    ComAddress varchar(128) NOT NULL,
    ComNumber int,
    ComType varchar(128),
    emailaddress varchar(128),
    status int,
    PRIMARY KEY(SupplierID)
);
