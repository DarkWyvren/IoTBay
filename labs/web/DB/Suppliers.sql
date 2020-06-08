/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mood35-Laptop
 */
DROP TABLE SUPPLIERDB;

CREATE TABLE SUPPLIERDB (
    SupplierID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    SupName varchar(128) NOT NULL,
    SupAddress varchar(128) NOT NULL,
    SupType varchar(128),
    SupEmail varchar(128),
    SupStatus int,
    PRIMARY KEY(SupplierID)
);

insert into supplierdb(Supname, SupAddress,SupType, SupEmail, SupStatus)
Values 
('UNIBITZ', 'U13 Featherweight Avenue, Springfield, NSW, 2630', 'RAM', 'unibitzservice@gmail.com', 1),
('Trial1', 'U17 Featherweight Avenue, Springfield, NSW, 2630', 'RAM', 'trial1@trial1.com',0),
('Trial2', 'U18 Feaht Street, Jenkins, NSW, 2888', 'Rasberry Pi', 'trial2@trial1.com',0);

select * from SupplierDB;