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
('UNIBITZ', 'U13 Featherweight Avenue, Springfield, NSW, 2630', 'Case', 'unibitzservice@gmail.com', 1),
('Trial1', '1 Test Avenue, Springfield, NSW, 2630', 'RAM', 'trial1@gmail.com',1),
('Trial2', '2 Test Street, Jenkins, NSW, 2888', 'CPU', 'trial2@gmail.com',1),
('Trial3', '3 Test Street, Jenkins, NSW, 2888', 'Fan', 'trial2@trial1.com',1),
('Trial4', 'Test Street, Jenkins, NSW, 2888', 'MotherBoard', 'trial3@trial1.com',1),
('Trial5', '23 Holup Avenue, Jenkins, NSW, 2888', 'Peripherals', 'trial4@trial1.com',0),
('Trial6', '27 James Street, Jenkins, NSW, 2888', 'Rasberry Pi', 'trial5@trial1.com',1),
('Trial7', 'U18 Jim Street, Jenkins, NSW, 2888', 'Rasberry Pi', 'trial6@trial1.com',0),
('Trial8', 'U18 HO Street, Jenkins, NSW, 2888', 'RAM', 'trial7@trial1.com',0),
('Trial9', 'U19 Featherweight Avenue, Batovile, NSW, 2696', 'CPU', 'trial8@trial3.com',0),
('Trial11', '1 Test Avenue, Springfield, NSW, 2630', 'RAM', 'trial9@gmail.com',0),
('Trial12', '2 Test Street, BlackPink, NSW, 1004', 'Fan', 'trial12@gmail.com',1),
('Trial13', '3 Test Street, Jenkins, NSW, 2888', 'Fan', 'trial13@trial1.com',0),
('Trial42', 'Test Street, Jenkins, NSW, 2888', 'MotherBoard', 'trial114@trial1.com',0),
('Trial58', '23 Holup Avenue, Jenkins, NSW, 2888', 'Peripherals', 'trial49@trial1.com',0),
('Trial60', '27 James Street, Jenkins, NSW, 2888', 'System', 'trial50@trial1.com',1),
('Trial79', 'U18 Jim Street, Jenkins, NSW, 2888', 'Rasberry Pi', 'trial61@trial1.com',1),
('HOMIN', 'U18 HO Street, Jenkins, NSW, 2888', 'RAM', 'trial76@trial1.com',1),
('Stired', 'U19 Featherweight Avenue, Batovile, NSW, 2696', 'CPU', 'trial28@trial3.com',1),
('Trial10', 'U20 Featherweight Avenue, Batovile, NSW, 2696', 'SSD', 'trial88@trial3.com',1);

select * from SupplierDB;