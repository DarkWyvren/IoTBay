/**
 *
 * @author Max
 */
DROP TABLE ORDER_HISTORY;
DROP TABLE ORDERDB;

CREATE TABLE ORDERDB (
    Order_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE Unique, 
    Shipping_Address varchar(255),
    /*Orderline_ID int NOT NULL,*/
    Status varchar(128) Unique,
    Product_ID int,
    Product_Name varchar(100),
    Product_Price double,
    Product_Quanity int,
    Total_Price double Unique,

    PRIMARY KEY(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    FOREIGN KEY(Product_ID) REFERENCES PRODUCTDB(Product_ID),
    FOREIGN KEY(Product_Name) REFERENCES PRODUCTDB(ProductName),
    FOREIGN KEY(Product_Price) REFERENCES PRODUCTDB(Price)
    /*FOREIGN KEY(Orderline_ID) REFERENCES ORDERLINE(Orderline_ID),*/
);

    INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Status, Product_ID, Product_Name, Product_Price, Product_Quanity, Total_Price)
    VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    INSERT INTO ORDERDB (Customer_ID, Date_Of_Order, Shipping_Address, Status, Product_ID, Product_Name, Product_Price, Product_Quanity, Total_Price)
    VALUES (2, '06/06/2020', '2 test st', Delivering, 1, 'IoTBay Device 1', 19.9, 1, 19.9);



CREATE TABLE ORDER_HISTORY (
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
    VALUES (2, 2, '06/06/2020', 'Delivering', 'Mastercard', 19.9, 19.9, 0);

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