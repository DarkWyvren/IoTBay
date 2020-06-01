/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mood35-Laptop
 * Created: 31/05/2020
 */

CREATE TABLE SUPPLIERDB (
    SupplierID int NOT NULL,
    ContactName varchar(128) NOT NULL,
    ComAddress varchar(128) NOT NULL,
    ComNumber int,
    ComType varchar(128),
    PRIMARY KEY(SupplierID)
);

alter table supplierdb
add emailaddress varchar(128);

insert into supplierdb
Values (00001, 'UNIBITZ', 'U13 Featherweight Avenue, Springfield, NSW, 2630', 0477 630 961, 'RAM', 'unibitzservice@gmail.com');