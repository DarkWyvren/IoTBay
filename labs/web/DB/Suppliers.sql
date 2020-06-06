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
    SupName varchar(128) NOT NULL,
    SupAddress varchar(128) NOT NULL,
    SupType varchar(128),
    SupEmail varchar(128),
    SupStatus int,
    PRIMARY KEY(SupplierID)
);
