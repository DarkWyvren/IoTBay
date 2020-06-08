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

CREATE TABLE PAYMENTDB (

    Payment_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Payment_METHOD varchar(128) NOT NULL,
    Order_id int NOT NULL,
    Payment_DATE DATE,
    Amount double NOT NULL,
    Credicard varchar(15),
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    PRIMARY KEY(Payment_ID)
    

);
    INSERT INTO PAYMENTDB (Payment_METHOD, Order_id, Payment_DATE, Amount, Credicard)
    VALUES ('Mastercard', 2, '06/06/2020', 19.9, 'B');
