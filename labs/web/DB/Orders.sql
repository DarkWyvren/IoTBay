/**
 *
 * @author Max
 */
DROP TABLE ORDERDB;
DROP TABLE ORDER_HISTORY;

CREATE TABLE ORDERDB (
    Order_ID int GENERATED ALWAYS AS IDENTITY NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE NOT NULL Unique, 
    Address varchar(255) NOT NULL,
    /*Orderline_ID int NOT NULL,*/
    Status varchar(128) Unique,
    Product_ID int NOT NULL,
    Product_Name varchar(100) NOT NULL,
    Product_Price double NOT NULL,
    Product_Quanity int NOT NULL,
    Total_Price double NOT NULL,

    PRIMARY KEY(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    /*FOREIGN KEY(Orderline_ID) REFERENCES ORDERLINE(Orderline_ID),*/
    FOREIGN KEY(Product_ID) REFERENCES PRODUCTDB(Product_ID)
    FOREIGN KEY(Product_Name) REFERENCES PRODUCTDB(ProductName)
    FOREIGN KEY(Product_Price) REFERENCES PRODUCTDB(Price)
);


CREATE TABLE ORDER_HISTORY (
    Order_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE NOT NULL,
    Status varchar(128),
    Payment_Method varchar(128),
    Original_Price double NOT NULL,
    Paid_Money double NOT NULL,
    Saved_Money double NOT NULL,
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    FOREIGN KEY(Date_Of_Order) REFERENCES ORDERDB(Date_Of_Order),
    FOREIGN KEY(Status) REFERENCES ORDERDB(Status),
    FOREIGN KEY(Original_Price) REFERENCES ORDERDB(Total_Price)
    /*FOREIGN KEY(Payment_Method) REFERENCES PAYMENTDB(Payment_Method)
    FOREIGN KEY(Paid_Money) REFERENCES PAYMENTDB(Payment_Amount)*/
);

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