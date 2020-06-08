/**
 *
 * @author Max
 */
/*DROP TABLE ORDER_HISTORY;*/
DROP TABLE ORDERDB;

CREATE TABLE ORDERDB (
    Order_ID int NOT NULL AUTO_INCREMENT,
    Customer_ID int,
    Date_Of_Order DATE, 
    Shipping_Address varchar(255),
    Product_ID int NOT NULL,
    Product_Quantity int,

    PRIMARY KEY(Order_ID),
    FOREIGN KEY(Product_ID) REFERENCES PRODUCTDB(ProductID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID)
);

INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (2, '2020-06-06', '15 IoTBat Test St Central|1241', 'IoTBay 64G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (3, '2020-06-06', '21 IoTBat ATest St Central|1241', 'IoTBay 4G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (5, '2020-06-06', '22 IoTBat BTest St Central|1241', 'IoTBay 64G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (6, '2020-06-07', '22 IoTBat CTest St Central|1241', 'IoTBay 4G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (4, '2020-06-07', '11 IoTBat DTest St Central|1241', 'IoTBay 32G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (7, '2020-06-07', '32 IoTBat ETest St Central|1241', 'IoTBay 64G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (1, '2020-06-07', '21IoTBat FTest St Central|1241', 'IoTBay 32G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (3, '2020-06-08', '55 IoTBat GTest St Central|1241', 'IoTBay 4G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (4, '2020-06-08', '33 IoTBat FTest St Central|1241', 'IoTBay 64G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (5, '2020-06-08', '53 IoTBat ETest St Central|1241', 'IoTBay 64G RAM', 3);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (2, '2020-06-09', '24 IoTBat ATest St Central|1241', 'IoTBay 32G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (5, '2020-06-09', '53 IoTBat CTest St Central|1241', 'IoTBay 8G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (11, '2020-06-09', '74 IoTBat DTest St Central|1241', 'IoTBay 32G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (17, '2020-06-09', '85 IoTBat WTest St Central|1241', 'IoTBay 64G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (31, '2020-06-10', '23 IoTBat STest St Central|1241', 'IoTBay 64G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (22, '2020-06-10', '44 IoTBat NTest St Central|1241', 'IoTBay 16G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (31, '2020-06-10', '42 IoTBat BTest St Central|1241', 'IoTBay 8G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (44, '2020-06-10', '32 IoTBat STest St Central|1241', 'IoTBay 64G RAM', 1);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (35, '2020-06-10', '64 IoTBat DTest St Central|1241', 'IoTBay 64G RAM', 2);
INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_Name, Product_Quantity)
VALUES (26, '2020-06-10', '78 IoTBat HTest St Central|1241', 'IoTBay 64G RAM', 3);

    /*INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_ID, Product_Quantity)
    VALUES (1, NULL, NULL, NULL, NULL);
    INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Product_ID, Product_Quantity)
    VALUES (2, '06/06/2020', '2 test st', 1, 1);*/
/*CREATE TABLE ORDER_HISTORY (
    Order_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE,
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    FOREIGN KEY(Date_Of_Order) REFERENCES ORDERDB(Date_Of_Order)
);

    INSERT INTO ORDER_HISTORY (Order_ID, Customer_ID, Date_Of_Order)
    VALUES (1, 1, NULL);
    INSERT INTO ORDER_HISTORY
    VALUES (2, 2, '06/06/2020');*/

/*CREATE TABLE ORDER_HISTORY (
    Order_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE,
    Status varchar(128),
    Payment_Method varchar(128),
    Original_Price double,
    Paid_Money double,
    Saved_Money double,
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    FOREIGN KEY(Date_Of_Order) REFERENCES ORDERDB(Date_Of_Order),
    FOREIGN KEY(Status) REFERENCES ORDERDB(Status),
    FOREIGN KEY(Payment_Method) REFERENCES PAYMENTDB(Payment_METHOD),
    FOREIGN KEY(Original_Price) REFERENCES ORDERDB(Total_Price),
    FOREIGN KEY(Paid_Money) REFERENCES PAYMENTDB(Amount)
);

    INSERT INTO ORDER_HISTORY (Order_ID, Customer_ID, Date_Of_Order, Status, Payment_Method, Original_Price, Paid_Money, Saved_Money)
    VALUES (1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
    INSERT INTO ORDER_HISTORY
    VALUES (2, 2, '06/06/2020', 'Delivering', 'Mastercard', 19.9, 19.9, 0);*/

/*CREATE TABLE ORDERLINE (
    Orderline_ID int NOT NULL,
    Product_ID int NOT NULL,
    Quanity int NOT NULL,
    
    PRIMARY KEY(Orderline_ID),
    FOREIGN KEY(Product_ID) REFERENCES PRODUCTDB(Product_ID),
);*/

/*CREATE TABLE STOCKDB (
    Product_ID int NOT NULL,
    Product_Name varchar(128) NOT NULL,
    Product_Price_Sale double NOT NULL,
    Category_ID int,
    
    PRIMARY KEY(Product_ID),
    FOREIGN KEY(Supplier_ID) REFERENCES CUSTOMERDB(SupplierID),
);

CREATE TABLE Inventory (
    Product_ID int NOT NULL,
    Product_Name varchar(128) NOT NULL,
    Quanity int NOT NULL,
    Product_Price_Purchase double NOT NULL,
    Supplier_ID int,   
    
    PRIMARY KEY(Product_ID),
    FOREIGN KEY(Supplier_ID) REFERENCES CUSTOMERDB(SupplierID),
);*/