/**
 *
 * @author Max
 */
CREATE TABLE ORDERDB (
    Order_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE NOT NULL Unique, 
    Address varchar(255) NOT NULL,
    /*Orderline_ID int NOT NULL,*/
    Status varchar(128) Unique,
    Product_ID int NOT NULL,
    Quanity int NOT NULL,

    PRIMARY KEY(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    /*FOREIGN KEY(Orderline_ID) REFERENCES ORDERLINE(Orderline_ID),*/
    FOREIGN KEY(Product_ID) REFERENCES PRODUCTDB(Product_ID)
);

CREATE TABLE ORDER_HISTORY (
    Order_ID int NOT NULL,
    Customer_ID int NOT NULL,
    Date_Of_Order DATE NOT NULL,
    Status varchar(128),
    
    FOREIGN KEY(Order_ID) REFERENCES ORDERDB(Order_ID),
    FOREIGN KEY(Customer_ID) REFERENCES CUSTOMERDB(Customer_ID),
    FOREIGN KEY(Date_Of_Order) REFERENCES ORDERDB(Date_Of_Order),
    FOREIGN KEY(Status) REFERENCES ORDERDB(Status)
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