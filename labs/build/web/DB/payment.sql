/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  10847
 * Created: Jun 7, 2020
 */


DROP TABLE PAYMENTDB;


DROP TABLE PAYMENTPREFDB;

CREATE TABLE PAYMENTPREFDB (
    Customer_ID int NOT NULL,
    Payment_ID int  NOT NULL AUTO_INCREMENT,
    Payment_METHOD varchar(128) NOT NULL,
    Credicard varchar(16),

FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    PRIMARY KEY(Payment_ID)
    

);


CREATE TABLE PAYMENTDB (

    Payment_ID int  NOT NULL AUTO_INCREMENT,
    Payment_METHOD varchar(128) NOT NULL,
    Order_id int NOT NULL,
    Payment_DATE DATE,
    Amount double NOT NULL,
    Credicard varchar(16),
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    PRIMARY KEY(Payment_ID)
    

);
    INSERT INTO PAYMENTDB (Payment_METHOD, Order_id, Payment_DATE, Amount, Credicard)
    VALUES ('Mastercard', 7, '2020-06-06', 19.90, '048493944900943');
