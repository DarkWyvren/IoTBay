/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

/**
 *
 * @author willi
 */
import uts.isd.model.CustomerBean;
import uts.isd.model.OrderBean;
import uts.isd.model.ProductBean;
import uts.isd.model.Supplier;
import uts.isd.model.Staff;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import javax.swing.text.DateFormatter;
import uts.isd.model.CustomerAccessLogBean;
import uts.isd.model.StaffAccessLogBean;
import uts.isd.model.payment;
import uts.isd.model.paymentprefBean;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }

    //Find user by email and password in the database   
    public CustomerBean findCustomer(String emaild) throws SQLException {  
        return findCustomer(emaild, "");
    }
    public CustomerBean findCustomer(String email, String password) throws SQLException {   
        String query = "SELECT * FROM sql12346043.CUSTOMERDB WHERE  Email='"+email+"'"+ (password.length()>0? " AND Password = '"+password+"'":"");
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String cust_email = rs.getString(2);
            String cust_password = rs.getString(3);
            System.out.println(cust_email+","+cust_password);
            if(cust_email.equals(email)&& cust_password.equals(password)){
                CustomerBean cb = new CustomerBean();
                cb.setEmail(cust_email);
                cb.setPassword(cust_password);
                cb.setName(rs.getString(4));
                cb.setId(rs.getInt(1));
                String[] dt = rs.getString(5).split("/");
                System.out.println(Arrays.toString(dt));
                
                cb.setDOB(Date.valueOf(rs.getString(5)));
                cb.setAddress(rs.getString(6));
                cb.setPhone(rs.getString(7));
                cb.setTitle(rs.getString(8));
                return cb;
            }
        }             
       return null;   
    } 
    
    
    

    //Add a user-data into the database   
    public void addCustomer(CustomerBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      String values=
              "'"
              +cb.getEmail()+"','"
              +cb.getPassword()+"','"
              +cb.getName()+"','"
              +format.format(cb.getDOB())+"','"+
              cb.getAddress()+"','"
              +cb.getPhone()+"','"
              +cb.getTitle()+"'"
              ;
      System.out.println(values);
      st.executeUpdate("INSERT INTO sql12346043.CUSTOMERDB(Email, Password,FullName,DOB,Address,Phone,Title)  VALUES("+values+")");   
      cb.setId(findCustomer(cb.getEmail(),cb.getPassword()).getId());

    }
    public CustomerAccessLogBean addCustomerLoginRecord(CustomerBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
      java.util.Date d =  new java.util.Date();
      String values=
              ""
              +cb.getId()+",'"
              +dateformat.format(d)+"','"
              +timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate("INSERT INTO sql12346043.CUSTOMER_SESSION(Customer_ID, LOGGEDIN_DATE,LOGGEDIN_TIME)  VALUES("+values+")");   
      CustomerAccessLogBean cab = new CustomerAccessLogBean();
      cab.setCustomer(cb);
      cab.setLoggedin(d);
      return cab;
    }
    
    public void endCustomerLoginRecord(CustomerAccessLogBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeformat = new SimpleDateFormat("H:m:s");
      java.util.Date d =  new java.util.Date();
      
      
      String values=
              "LOGGEDOUT_DATE = '"+dateformat.format(d)+"',"+
              "LOGGEDOUT_TIME = '"+timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate(
                "UPDATE sql12346043.CUSTOMER_SESSION SET "+values+" "
                        + "WHERE"
                        + " Customer_ID = "+cb.getCustomerid()+
                          " AND LOGGEDIN_TIME = '"+timeformat.format(cb.getLoggedin())+"'"+
                          " AND LOGGEDIN_DATE = '"+dateformat.format(cb.getLoggedin())+"'" );   
      cb.setLoggedout(d);
    }
    
    public ArrayList<CustomerAccessLogBean> listCustomerLoginRecord(int cid) throws SQLException, ParseException {                   
        ArrayList<CustomerAccessLogBean> result = new ArrayList<>();
      String query = "SELECT * FROM sql12346043.CUSTOMER_SESSION WHERE  Customer_ID="+cid;
        ResultSet rs = st.executeQuery(query);
        
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        while(rs.next()){
             CustomerAccessLogBean cb = new CustomerAccessLogBean();
                cb.setCustomerid(cid);
                cb.setLoggedin(timeformat.parse(rs.getString(2)+" "+rs.getString(3)));
                cb.setLoggedout(rs.getString(4)==null?cb.getLoggedin():(timeformat.parse(rs.getString(4)+" "+rs.getString(5))));
                //apply time here.
                //add search and delete and ur done yey
                result.add(cb);
        }
        return result;
    }

    //update a user details in the database   
    public void updateCustomer(CustomerBean cb) throws SQLException {       
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String values=
              "Email = '"+cb.getEmail()+"',"+
              "Password = '"+cb.getPassword()+"',"+
              "FullName = '"+cb.getName()+"',"+
              "DOB = '"+format.format(cb.getDOB())+"',"+
              "Address = '"+cb.getAddress()+"',"+
              "Phone = '"+cb.getPhone()+"',"+
              "Title = '"+cb.getTitle()+"'"
              ;
        System.out.println(values);
        st.executeUpdate("UPDATE sql12346043.CUSTOMERDB SET "+values+" WHERE Customer_ID ="+cb.getId());   

    }       

    //delete a user from the database   
    public void deleteCustomer(String email) throws SQLException{       
       st.executeUpdate("DELETE FROM sql12346043.CUSTOMERDB WHERE Email='"+email+"'");
    }


    
    
    
    
    
    
    

//PRODUCT 
    //Find Product by ID in the database   
    
    // public CustomerBean findCustomer(String emaild) throws SQLException {  
    //    return findCustomer(emaild, "");
    // }
    //PRODUCT INFO
    //can view product info
     public void showProduct(int id, String name, double price, String category, int supplierid) throws SQLException{
        String query = "SELECT FROM * sql12346043.PRODUCTDB";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            id = rs.getInt(3);
            name = rs.getString(4);
            price = rs.getDouble(5);
            category = rs.getString(6);
            supplierid = rs.getInt(7);
            
        }
     }

    public ProductBean findProduct(String Product_ID) throws SQLException {   
        String query = "SELECT * FROM sql12346043.PRODUCTDB WHERE  Product_ID='"+Product_ID+"'";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String prod_id = rs.getString(2);
            System.out.println(prod_id);
            if(prod_id.equals(Product_ID)){
                ProductBean pb = new ProductBean();
                pb.setID(rs.getInt(3));
                pb.setName(rs.getString(4));
                pb.setPrice(rs.getDouble(5));
                pb.setCategory(rs.getString(6));
                pb.setSupplier(rs.getInt(7));
                
                String[] dt = rs.getString(5).split("/");
                System.out.println(Arrays.toString(dt));
                
                return pb;
            }
        }
      return null;
    }
    // Product Information
    

    // view Product Information
    
    public void showProduct(int ProID, String ProName, double ProPrice, String ProCategory, int ProSupplierid, int ProQuantity) throws SQLException{
    String query = "SELECT FROM * sql12346043.PRODUCTDB";
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
        
            ProID = rs.getInt(1);
            ProName = rs.getString(2);
            ProPrice = rs.getDouble(3);
            ProCategory = rs.getString(4);
            ProSupplierid = rs.getInt(5);
            ProQuantity = rs.getInt(6);
        }
     }
    
    // Find Products from DB using Product ID (Primary Key) 
        public ProductBean findProduct(String ProName, String ProCategory) throws SQLException {   
            String query = "SELECT * FROM sql12346043.PRODUCTDB WHERE  ProductName='"+ProName+"' AND Password = '"+ProCategory+"'";
            ResultSet rs = st.executeQuery(query);
        
             while(rs.next()){
                    String P_Name = rs.getString(2);
                    String P_Category = rs.getString(4);
            

            if(P_Name.equals(ProName) && P_Category.equals(ProCategory) ){ 
                int P_ID = rs.getInt(1);               
                Double P_Price = rs.getDouble(3);
                Integer P_SupplierID = rs.getInt(5);
                Integer P_Quantity = rs.getInt(6);

               // int C_Status = rs.getInt(6); //

                System.out.println("Product Name: " +P_Name);
                return new ProductBean (P_ID, P_Name, P_Price, P_Category, P_SupplierID, P_Quantity); 
            }
        }         
       return null;   
    }
    
    
           public ProductBean getProduct(int ProID) throws SQLException {   
        ProductBean product = null;
            String query = "SELECT * FROM sql12346043.PRODUCTDB WHERE  ProductID="+ProID+"";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int P_ID = rs.getInt(1);
                System.out.println(P_ID);
                String P_Name = rs.getString(2);
                Double P_Price = rs.getDouble(3);
                String P_Category = rs.getString(4);
                Integer P_SupplierID = rs.getInt(5);
                Integer P_Quantity = rs.getInt(6);
                
                System.out.println("Product Name: " +P_Name);
                product = new ProductBean (P_ID, P_Name, P_Price, P_Category, P_SupplierID, P_Quantity); 
                return product;     
            } 
            return null;     
    }
           
    // Add a Product into the DB
    
    public void addProduct(ProductBean pb) throws SQLException {
        String values=
              //"ProductID = '"+sb.getProductID()+"',"+
              "'"+pb.getName()+"',"+
              ""+pb.getPrice()+","+
              "'"+pb.getCategory()+"',"+
              ""+pb.getSupplier()+","+
              ""+pb.getQuantity()+""
              ;
        System.out.println(values);
        st.executeUpdate("INSERT INTO sql12346043.PRODUCTDB(ProductName, ProductPrice, Category, SupplierID, Quantity)  VALUES("+values+")");   

    }
    
    // Delete a Product from the DB 
    
    public void deleteProduct(ProductBean pd) throws SQLException{
        st.executeUpdate("DELETE FROM sql12346043.PRODUCTDB WHERE ProductID =" +pd.getID());
  
    }
    
    // Update a Product in the DB
    
    public void updateProduct(ProductBean pb) throws SQLException {
        String values=
              //"SupplierID = '"+sb.getSupplierID()+"',"+
                
              //"ProductID = '"+pb.getID()+","+
              "ProductName = '"+pb.getName()+"',"+
              "ProductPrice = "+pb.getPrice()+","+
              "Category = '"+pb.getCategory()+"',"+
              "SupplierID = "+pb.getSupplier()+","+
              "Quantity = "+pb.getQuantity()+""
              ;
        System.out.println(+pb.getID());
        st.executeUpdate("UPDATE sql12346043.PRODUCTDB SET "+values+" WHERE ProductID ="+pb.getID());   

    }  
    
    // Show Products from the DB (Fetch Array List) 


    public ArrayList<ProductBean> fetchProductList() throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.PRODUCTDB";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<ProductBean> listProduct = new ArrayList(); 
       
        while(rs.next()){
                int P_ID = rs.getInt(1);
                String P_Name = rs.getString(2);
                Double P_Price = rs.getDouble(3);
                String P_Category = rs.getString(4);
                Integer P_SupplierID = rs.getInt(5);
                Integer P_Quantity = rs.getInt(6);
            
            ProductBean ProductFromDB = new ProductBean(P_ID, P_Name, P_Price, P_Category, P_SupplierID, P_Quantity);
            listProduct.add(ProductFromDB);
            
        }     
        return listProduct;
    }
    
    


 
    
    
    
    
    
    
    
    
    
    
    
    //SUPPLIER INFORMATION
    //can view supplier info
     public void showSupplier(String ContactName, String CompanyAddress,int ConNumber, String CompanyType, String CompanyEmail, int Status) throws SQLException{
        String query = "SELECT FROM * sql12346043.SUPPLIERDB";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            ContactName = rs.getString(2);
            CompanyAddress = rs.getString(3);
            ConNumber = rs.getInt(4);
            CompanyType = rs.getString(5);
            CompanyEmail = rs.getString(6);
            Status = rs.getInt(7);
        } 
     }
    

        //find supplier from db
    /* public Supplier findSupplier(String ContactName, String CompanyEmail) throws SQLException {   
        String query = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  CONTACTNAME='"+ContactName+"'"+ (" AND EMAILADDRESS = '"+CompanyEmail+"'");
  */
           //find supplier from db using supplier id

     public ArrayList<Supplier> findSupplier(String CompanyName, String CompanyType) throws SQLException {   
        String query = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  SupName='"+CompanyName+"' OR SupType = '"+CompanyType.trim()+"'";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Supplier> listSupplier = new ArrayList(); 
        
        while(rs.next()){
            
            String C_Name = rs.getString(2);
            String C_Type = rs.getString(4);
            //if(C_Name.equals(CompanyName) || C_Type.equals(CompanyType) ){ 
                int S_ID = rs.getInt(1);               
                String C_Address = rs.getString(3);
                String C_Email = rs.getString(5);
                int C_Status = rs.getInt(6);
                System.out.println("Company Name: " +C_Name);
                Supplier sd = new Supplier (S_ID, C_Name, C_Address, C_Type, C_Email, C_Status); 
                listSupplier.add(sd);   
            //}
        }         
       return listSupplier;   
    }
     
    public Object getSupEmail(Supplier sb) throws SQLException {
    String query = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  SupEmail= '"+sb.getCompanyEmail()+"'";
    ResultSet rs = st.executeQuery(query);
     while(rs.next()){
         return rs.getString(5);
     }
    System.out.println("the email was: " +rs);
    return null;
//To change body of generated methods, choose Tools | Templates.
    }
     
        public Supplier getSupplier(int Supplier_id) throws SQLException {   
        Supplier supplier = null;
            String query = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  SupplierID="+Supplier_id+"";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int S_ID = rs.getInt(1);
                System.out.println(S_ID);
                String C_Name = rs.getString(2);
                String C_Address = rs.getString(3);
                String C_Type = rs.getString(4);
                String C_Email = rs.getString(5);
                int C_Status = rs.getInt(6);
                System.out.println("Company Name: " +C_Name);
                supplier = new Supplier (S_ID, C_Name, C_Address, C_Type, C_Email, C_Status); 
            } return supplier;     
    }
      
    //Add a supplier into the db
    public void addSupplier(Supplier sb) throws SQLException {
        String values=
              //"SupplierID = '"+sb.getSupplierID()+"',"+
              "'"+sb.getCompanyName()+"',"+
              "'"+sb.getCompanyAddress()+"',"+
              "'"+sb.getCompanyType()+"',"+
              "'"+sb.getCompanyEmail()+"',"+
              ""+sb.getCompanyStatus()+""
              ;
        System.out.println(values);
        st.executeUpdate("INSERT INTO sql12346043.SUPPLIERDB(SupName,SupAddress,SupType,SupEmail,SupStatus)  VALUES("+values+")");   

    }  
     
    /*public void addSupplier (String CompanyName, String CompanyAddress, String CompanyType, String CompanyEmail, int CompanyStatus) throws SQLException {
        st.executeUpdate("INSERT INTO sql12346043.SUPPLIERDB" + "VALUES ("+CompanyName+", "+CompanyAddress+", "+CompanyType+", "+CompanyEmail+", "+CompanyStatus+")");
    }*/
    //Update a Suppliers information
    public void updateSupplier(Supplier sb) throws SQLException {
        String values=
              //"SupplierID = '"+sb.getSupplierID()+"',"+
              "SupName = '"+sb.getCompanyName()+"',"+
              "SupAddress = '"+sb.getCompanyAddress()+"',"+
              "SupType = '"+sb.getCompanyType()+"',"+
              "SupEmail = '"+sb.getCompanyEmail()+"',"+
              "SupStatus = "+sb.getCompanyStatus()+""
              ;
        System.out.println(+sb.getSupplierID());
        st.executeUpdate("UPDATE sql12346043.SUPPLIERDB SET "+values+" WHERE SupplierID ="+sb.getSupplierID());   

    }  
    
    /*public void updateSupplier (int SupplierID, String CompanyName, String CompanyAddress, String CompanyType, String CompanyEmail, int Status) throws SQLException {
        st.executeUpdate("UPDATE sql12346043.SUPPLIERDB SET SupName ="+CompanyName+", SET SupAddress  "+CompanyAddress+", SET SupType "+CompanyType+",SET SupEmail "+CompanyEmail+", SET SupStatus "+Status+" WHERE SupplierID ='"+SupplierID+"'");
    }*/
    
    
    //delete a supplier from db

    public void deleteSupplier(Supplier sd) throws SQLException{
        st.executeUpdate("DELETE FROM sql12346043.SUPPLIERDB WHERE SupplierID =" +sd.getSupplierID());
    }

    public ArrayList<Supplier> fetchSupplierList() throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.SUPPLIERDB";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Supplier> listSupplier = new ArrayList(); 
       
        while(rs.next()){
            int S_ID = rs.getInt(1);
            String C_NAME = rs.getString(2);
            String C_ADDRESS = rs.getString(3);
            String C_TYPE = rs.getString(4);
            String C_EMAIL = rs.getString(5);
            int C_STATUS = rs.getInt(6);
            
            Supplier SupplierFromDB = new Supplier(S_ID, C_NAME, C_ADDRESS, C_TYPE, C_EMAIL, C_STATUS);
            listSupplier.add(SupplierFromDB);
            
        }     
        return listSupplier;
    }
    
     public ArrayList<Supplier> Oneline(int Supplier_id) throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  SupplierID='"+Supplier_id+"'";;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Supplier> oneline = new ArrayList(); 
       
        while(rs.next()){
            int S_ID = rs.getInt(1);
            String C_NAME = rs.getString(2);
            String C_ADDRESS = rs.getString(3);
            String C_TYPE = rs.getString(4);
            String C_EMAIL = rs.getString(5);
            int C_STATUS = rs.getInt(6);
            
            Supplier SupplierFromDB = new Supplier(S_ID, C_NAME, C_ADDRESS, C_TYPE, C_EMAIL, C_STATUS);
            oneline.add(SupplierFromDB);
            
            
        }     
        return oneline;
    }
    /*public Arraylist<Supplier> fetchSupplierList(int sid) throws SQLException{
        Arraylist<Supplier> temp = new Arraylist();    
        String fetch = "SELECT * FROM sql12346043.SUPPLIERDB where SupplierID" +sid;
        ResultSet rs = st.executeQuery(fetch);
            while(rs.next()){
            String C_Name = rs.getString(2);
            String C_Address = rs.getString(3);
            String C_Type = rs.getString(4);
            String C_Email = rs.getString(5);
            int C_Status = rs.getInt(6);
        //    temp.add(new Supplier(C_Name, C_Address, C_Type, C_Email, C_Status));
        } 
            
            return temp;
    }   
     
    //check if supplier exists using company name and type
    public boolean checkSupplier(String ContactName, String CompanyType) throws SQLException{
       String fetch = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  CONTACTNAME='"+ContactName+"'"+ (" AND COMTYPE = '"+CompanyType+"'");
       ResultSet rs = st.executeQuery(fetch);
       
        while(rs.next()){
            String C_Name = rs.getString(2);
            String C_Type = rs.getString(4);
            if(C_Name.equals(ContactName) && C_Type.equals(CompanyType)){
                return true;
            }
        }
        return false; 
    }
    */

    
    
//Order Management [MVC]
    //Find all orders based on Date_Of_Order
    public OrderBean findOrder(Date Date_Of_Order) throws SQLException {  
        return findOrder(Date_Of_Order, 0);
    }
    //Find all orders based on Date_Of_Order
    public OrderBean findOrder(int Order_ID) throws SQLException {  
        return findOrder(null, Order_ID);
    }
    //Find the specific order using Date_Of_Order and Order_ID


    public OrderBean findOrder(Date Date_Of_Order, int Order_ID) throws SQLException {   
        String query = "SELECT * FROM sql12346043.ORDERDB WHERE Date_Of_Order='"+Date_Of_Order+"' AND Order_ID = '"+Order_ID;

        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String order_date = rs.getString(3);
            int ord_id = rs.getInt(1);
            System.out.println(order_date+","+ord_id);
            if(order_date.equals(Date_Of_Order)&& ord_id == (Order_ID)){
                OrderBean ob = new OrderBean();
                ob.setOrderId(ord_id);
                int CustomerId = rs.getInt(2);
                ob.setCustomerId(CustomerId);
                String[] dt = rs.getString(2).split("/");
                System.out.println(Arrays.toString(dt));
                ob.setDOO(Date.valueOf(rs.getString(3)));
                
                ob.setShippingAddress(rs.getString(4));
                ob.setProductID(rs.getInt(5));
                ob.setProductQuantity(rs.getInt(6));
 
                return ob;
            }
        }            
        return null;   
    }
        
    //Add a order-data into the database   
    public void addOrder(OrderBean ob) throws SQLException {                   
    //code for add-operation    
              SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String values=
              ""+ob.getCustomerId()+","+
              "'"+dateformat.format(ob.getDOO())+"',"+
              "'"+ob.getShippingAddress()+"',"+
              ""+ob.getProductID()+","+
              ""+ob.getProductQuantity()+""
              ;
        System.out.println(values); 

        st.executeUpdate("INSERT INTO sql12346043.ORDERDB(Customer_ID, Date_Of_Order, Shipping_Address, Product_ID, Product_Quantity)  VALUES("+values+")");    

    }
    
    //update a order details in the database   

    public void updateOrder(OrderBean ob) throws SQLException {       
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String values=
              "Customer_ID = '"+ob.getCustomerId()+"',"+
              "Date_Of_Order = '"+dateformat.format(ob.getDOO())+"',"+
              "Shipping_Address = '"+ob.getShippingAddress()+"',"+
              "Product_ID = "+ob.getProductID()+","+
              "Product_Quantity = "+ob.getProductQuantity()+""
              ;
        System.out.println(values);
        st.executeUpdate("UPDATE sql12346043.ORDERDB SET "+values+" WHERE Order_ID ="+ob.getOrderId());
    } 
    
    //delete a order from the database   
    public void deleteOrder(OrderBean od) throws SQLException{       
       //code for delete-operation   
       st.executeUpdate("DELETE FROM sql12346043.ORDERDB WHERE Order_ID ="+od.getOrderId()+"");
    }
    public void deleteOrder(int od) throws SQLException{       
       //code for delete-operation   
       st.executeUpdate("DELETE FROM sql12346043.ORDERDB WHERE Order_ID ="+od+"");
    }
    public OrderBean getOrderBean(int order_ID) throws SQLException {   
        OrderBean orderBean = null;
            String query = "SELECT * FROM sql12346043.ORDERDB WHERE Order_ID="+order_ID+"";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int OrderId = rs.getInt(1);
                int CustomerId = rs.getInt(2);
                Date DOO = rs.getDate(3);
                String ShippingAddress = rs.getString(4);
                int productID= rs.getInt(5);
                int ProductQuantity = rs.getInt(6);
                System.out.println("Order id: " + OrderId);
                orderBean = new OrderBean (OrderId, CustomerId, DOO, ShippingAddress, productID, ProductQuantity); 
            } return orderBean;     
    }
    
        public void showOrder(int OrderId, int CustomerId, Date DOO, String ShippingAddress, int ProductId, int ProductQuantity) throws SQLException{
        String query = "SELECT FROM * sql12346043.ORDERDB";
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            OrderId = rs.getInt(1);
            CustomerId = rs.getInt(2);
            DOO = rs.getDate(3);
            ShippingAddress = rs.getString(4);
            ProductId = rs.getInt(5);
            ProductQuantity = rs.getInt(6);
        } 
    }
     
        public ArrayList<OrderBean> fetchOrderList() throws SQLException{
           
        String fetch = "SELECT ORDERDB.*,PRODUCTDB.ProductName " +
                        "FROM ORDERDB INNER JOIN PRODUCTDB ON ORDERDB.Product_ID = PRODUCTDB.ProductID";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderBean> listOrder = new ArrayList(); 

        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            String ShippingAddress = rs.getString(4);
            int productID= rs.getInt(5);
            int ProductQuantity = rs.getInt(6);
  
            OrderBean OrderFromDB = new OrderBean(OrderId, CustomerId, DOO, ShippingAddress, productID, ProductQuantity);
            OrderFromDB.setProduct(new ProductBean(productID, rs.getString(7), 0.0, "", 0, 0));
            listOrder.add(OrderFromDB);
            
        }     
        return listOrder;
    }
    
     public ArrayList<OrderBean> OrderLine(int Customer_id) throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.ORDERDB WHERE Customer_ID='"+Customer_id+"'";;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderBean> OrderLine = new ArrayList(); 
       
        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            String ShippingAddress = rs.getString(4);
            int productID= rs.getInt(5);
            int ProductQuantity = rs.getInt(6);
            
            OrderBean OrderFromDB = new OrderBean(OrderId, CustomerId, DOO, ShippingAddress, productID, ProductQuantity);
            OrderLine.add(OrderFromDB);         
        }     
        return OrderLine;
    }
    
     public void showOrderHistory(int OrderId, int CustomerId, Date DOO) throws SQLException{
        String query = "SELECT FROM * sql12346043.ORDERDB";
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            OrderId = rs.getInt(1);
            CustomerId = rs.getInt(2);
            DOO = rs.getDate(3);
        } 
    }
     
        public ArrayList<OrderBean> fetchOrderHistoryList() throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.ORDERDB";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderBean> listOrderHistory = new ArrayList(); 

        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            
            OrderBean OrderHistoryFromDB = new OrderBean(OrderId, CustomerId, DOO);
            listOrderHistory.add(OrderHistoryFromDB);
            
            
        }     
        return listOrderHistory;
    }
    
     public ArrayList<OrderBean> OrderHistoryLine(int Customer_id) throws SQLException{
           
        String fetch = "SELECT * FROM sql12346043.ORDERDB WHERE Customer_ID='"+Customer_id+"'";;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderBean> OrderHistoryLine = new ArrayList(); 
       
        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            
            OrderBean OrderHistoryFromDB = new OrderBean(OrderId, CustomerId, DOO);
            OrderHistoryLine.add(OrderHistoryFromDB);
            
            
        }     
        return OrderHistoryLine;
    }
     
     /*
         //Add a order-data into the database   
    public void addOrderHistory(OrderHistoryBean ohb) throws SQLException {                   
    //code for add-operation    
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String values=
              "'"
              +ohb.getOrderId()+"','"
              +ohb.getCustomerId()+"','"
              +format.format(ohb.getDOO())+"','"
              +ohb.getStatus()+"','"
              +ohb.getPaymentMethod()+"','"
              +ohb.getOriginalPrice()+"','"
              +ohb.getPaidMoney()+"','"
              +ohb.getSavedMoney()+"'"
              ;
        System.out.println(values);
        st.executeUpdate("INSERT INTO APP.ORDER_HISTORY(Order_ID, Customer_ID, Date_Of_Order, Status, Payment_Method, Original_Price, Paid_Money, Saved_Money)  VALUES("+values+")");    
    }
    
    //update a order details in the database   
    public void updateOrderHistory(OrderHistoryBean ohb) throws SQLException {       
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String values=
              "Order_ID = '"+ohb.getCustomerId()+"',"+
              "Customer_ID = '"+ohb.getCustomerId()+"',"+
              "Date_Of_Order = '"+format.format(ohb.getDOO())+"',"+
              "Status = '"+format.format(ohb.getStatus())+"',"+
              "Payment_Method = '"+ohb.getPaymentMethod()+"',"+
              "Original_Price = '"+ohb.getOriginalPrice()+"',"+
              "Paid_Money = '"+ohb.getPaidMoney()+"',"+
              "Saved_Money = '"+ohb.getSavedMoney()+"'"
              ;
        System.out.println(values);
        st.executeUpdate("UPDATE APP.ORDER_HISTORY SET "+values+" WHERE Order_ID ="+ohb.getOrderId());
    } 
    
    //delete a order from the database   
    public void deleteOrderHistory(int Order_ID) throws SQLException{       
       //code for delete-operation   
       st.executeUpdate("DELETE FROM APP.ORDER_HISTORY WHERE Order_ID ='"+Order_ID+"'");
    }
    
        public void showOrderHistory(int OrderId, int CustomerId, Date DOO, String Status, String PaymentMethod, double OriginalPrice, double PaidMoney, double SavedMoney) throws SQLException{
        String query = "SELECT FROM * APP.ORDER_HISTORY";
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            OrderId = rs.getInt(1);
            CustomerId = rs.getInt(2);
            DOO = rs.getDate(3);
            Status = rs.getString(4);
            PaymentMethod = rs.getString(5);
            OriginalPrice = rs.getDouble(6);
            PaidMoney = rs.getDouble(7);
            SavedMoney = rs.getDouble(7);
        } 
    }
     
        public ArrayList<OrderHistoryBean> fetchOrderHistoryList() throws SQLException{
           
        String fetch = "SELECT * FROM APP.ORDER_HISTORY";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderHistoryBean> listOrderHistory = new ArrayList(); 

        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            String Status = rs.getString(4);
            String PaymentMethod = rs.getString(5);
            double OriginalPrice = rs.getDouble(6);
            double PaidMoney = rs.getDouble(7);
            double SavedMoney = rs.getDouble(8);
            
            OrderHistoryBean OrderHistoryFromDB = new OrderHistoryBean(OrderId, CustomerId, DOO, Status, PaymentMethod, OriginalPrice, PaidMoney, SavedMoney);
            listOrderHistory.add(OrderHistoryFromDB);
            
            
        }     
        return listOrderHistory;
    }
    
     public ArrayList<OrderHistoryBean> OrderHistoryLine(int Customer_id) throws SQLException{
           
        String fetch = "SELECT * FROM APP.ORDER_HISTORY WHERE Customer_ID='"+Customer_id+"'";;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderHistoryBean> OrderHistoryLine = new ArrayList(); 
       
        while(rs.next()){
            int OrderId = rs.getInt(1);
            int CustomerId = rs.getInt(2);
            Date DOO = rs.getDate(3);
            String Status = rs.getString(4);
            String PaymentMethod = rs.getString(5);
            double OriginalPrice = rs.getDouble(6);
            double PaidMoney = rs.getDouble(7);
            double SavedMoney = rs.getDouble(8);
            
            OrderHistoryBean OrderHistoryFromDB = new OrderHistoryBean(OrderId, CustomerId, DOO, Status, PaymentMethod, OriginalPrice, PaidMoney, SavedMoney);
            OrderHistoryLine.add(OrderHistoryFromDB);
            
            
        }     
        return OrderHistoryLine;
    }*/
    
    
    
    
    
    

    
    //STAFF INFO
        //find staff from db
    
    public Staff findStaff(String email, String password) throws SQLException {   
        String query = "SELECT * FROM sql12346043.STAFF WHERE Email='"+email+"'"+ (password.length()>0? " AND Password = '"+password+"'":"");
        ResultSet rs = st.executeQuery(query);
        Staff staff = null;
        while(rs.next()){
            int Id = rs.getInt(1);
            System.out.println(Id);
            String Name = rs.getString(4);
            String Address = rs.getString(5);
            String Pos = rs.getString(6);
            String Email = rs.getString(2);
            int Status = rs.getInt(7);
            staff = new Staff (Id, Email,Name, Address, Pos, Status); 
            staff.setPassword(rs.getString(3));
            return staff;   
        }       
       return null;   
    } 
     public ArrayList<Staff> findStaff(String name) throws SQLException 
     {   
        String query = "SELECT * FROM sql12346043.STAFF WHERE FULLNAME='"+name+"'";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Staff> result = new ArrayList();
        while(rs.next())
        {
            String FullName = rs.getString(3);
            String Email = rs.getString(2);
            String Address = rs.getString(4);
            String Position = rs.getString(5);
            int Status = rs.getInt(6);
            int Id = rs.getInt(1);
            if(FullName.equals(name))
            {
                result.add(new Staff(Id, Email, FullName, Address, Position, Status));
            }
        }
        return result;
     }
     
     public Staff getStaff(int Staff_id) throws SQLException {   
        Staff staff = null;
        //(ID,Email, Password,FullName,Address,Pos, Status) 
            String query = "SELECT * FROM sql12346043.STAFF WHERE  ID="+Staff_id+"";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int Id = rs.getInt(1);
                System.out.println(Id);
                String Name = rs.getString(4);
                String Address = rs.getString(5);
                String Pos = rs.getString(6);
                String Email = rs.getString(2);
                int Status = rs.getInt(7);
                System.out.println("Staff Name: " +Name);
                staff = new Staff (Id, Name, Address, Pos, Email, Status); 
                staff.setPassword(rs.getString(3));
            } return staff;     
    }
     
     public void deleteStaff(Staff stf) throws SQLException{
        st.executeUpdate("DELETE FROM sql12346043.STAFF WHERE ID =" +stf.getId());
  
    }

     public void updateStaff(Staff stf) throws SQLException {
        String values=
              
              "FullName = '"+stf.getFullName()+"',"+
              "Address = '"+stf.getAddress()+"',"+
              "Pos = '"+stf.getPosition()+"',"+
              "Email = '"+stf.getEmail()+"'"
              ;
        System.out.println(+stf.getId());
        st.executeUpdate("UPDATE sql12346043.STAFF SET "+values+" WHERE ID ="+stf.getId());   

    }
    
    public payment findpayment(String Payment_ID, String Payment_DATE) throws SQLException {   
        String query = "SELECT * FROM sql12346043.PAYMENTDB WHERE  PAYMENTID='"+Payment_ID+"'"+ (" AND PAYMENTDATE = '"+Payment_DATE+"'");
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            String P_ID = rs.getString(1);
            String P_DATE = rs.getString(4);
            System.out.println(P_ID+","+P_DATE);
            
            if(P_ID.equals(Payment_ID)&& P_DATE.equals(Payment_DATE)){
                String P_METHOD = rs.getString(2);
                String P_CREDITCARD = rs.getString(5);
                int P_AMOUNT = rs.getInt(6);
                return new payment (P_ID, P_DATE, P_METHOD, P_CREDITCARD, P_AMOUNT); 

            }
        }         
       return null;   
    }

       //Add a staff into the db
    public void addStaff (Staff stf) throws SQLException {
       String values = 
               
               "'"+stf.getEmail() + "',"+
              "'"+stf.getFullName() + "',"+
                "'"+stf.getAddress() + "',"+
              "'"+stf.getPosition() + "',"+
               ""+stf.getStatus() + "";


       st.executeUpdate("INSERT INTO sql12346043.STAFF(EMAIL, FULLNAME, ADDRESS, POS, STATUS) VALUES("+values+")");

    }
     
        public ArrayList<Staff> fetchStaffList() throws SQLException
        {


            String fetch = "SELECT * FROM sql12346043.STAFF";

            ResultSet rs = st.executeQuery(fetch);
            ArrayList<Staff> temp1 = new ArrayList();
            while(rs.next()){
            String Name = rs.getString(3);
            String Address = rs.getString(4);
            String Position = rs.getString(5);
            String Email = rs.getString(2);
            int Id = rs.getInt(1);
            int Status = rs.getInt(6);
            temp1.add(new Staff(Id, Email, Name, Address, Position, Status));
        } 
            return temp1;
        }

        
     public StaffAccessLogBean addStaffLoginRecord(Staff cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
      java.util.Date d =  new java.util.Date();
      String values=
              ""
              +cb.getId()+",'"
              +dateformat.format(d)+"','"
              +timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate("INSERT INTO sql12346043.STAFF_SESSION(Staff_ID, LOGGEDIN_DATE,LOGGEDIN_TIME)  VALUES("+values+")");   
      StaffAccessLogBean cab = new StaffAccessLogBean();
      cab.setStaff(cb);
      cab.setLoggedin(d);
      return cab;
    }
    
    public void endStaffLoginRecord(StaffAccessLogBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeformat = new SimpleDateFormat("H:m:s");
      java.util.Date d =  new java.util.Date();
      
      
      String values=
              "LOGGEDOUT_DATE = '"+dateformat.format(d)+"',"+
              "LOGGEDOUT_TIME = '"+timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate(
                "UPDATE sql12346043.STAFF_SESSION SET "+values+" "
                        + "WHERE"
                        + " Staff_ID = "+cb.getStaffid()+
                          " AND LOGGEDIN_TIME = '"+timeformat.format(cb.getLoggedin())+"'"+
                          " AND LOGGEDIN_DATE = '"+dateformat.format(cb.getLoggedin())+"'" );   
      cb.setLoggedout(d);
    }
    
    public ArrayList<StaffAccessLogBean> listStaffLoginRecord(int cid) throws SQLException, ParseException {                   
        ArrayList<StaffAccessLogBean> result = new ArrayList<>();
      String query = "SELECT * FROM sql12346043.STAFF_SESSION WHERE  Staff_ID="+cid;
        ResultSet rs = st.executeQuery(query);
        
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        while(rs.next()){
             StaffAccessLogBean cb = new StaffAccessLogBean();
                cb.setStaffid(cid);
                cb.setLoggedin(timeformat.parse(rs.getString(2)+" "+rs.getString(3)));
                cb.setLoggedout(rs.getString(4)==null?cb.getLoggedin():(timeformat.parse(rs.getString(4)+" "+rs.getString(5))));
                //apply time here.
                //add search and delete and ur done yey
                result.add(cb);
        }
        return result;
    }   
        
    
    public void addPaymentPref(paymentprefBean pb) throws SQLException {
        st.executeUpdate("INSERT INTO sql12346043.PAYMENTPREFDB(Customer_ID, Payment_METHOD, Credicard)" + "VALUES ('"+pb.getCusid()+"', '"+pb.getPayment_METHOD()+"', '"+pb.getCreditcard_Details()+"')");
    }
    
    public void updatePaymentPref(paymentprefBean pb) throws SQLException {
        st.executeUpdate("UPDATE INTO sql12346043.PAYMENTPREFDB SET Payment_METHOD= '"+pb.getPayment_METHOD()+"', Credicard='"+pb.getCreditcard_Details()+"' WHERE Payment_ID = "+pb.getPayment_ID());
    }
    
    public paymentprefBean getPaymentPref(int cusid) throws SQLException {
        String query = "SELECT * FROM sql12346043.PAYMENTPREFDB WHERE  Customer_ID='"+cusid+"'";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            String P_ID = rs.getString(2);
            String CRE = rs.getString(4);
             String Meth = rs.getString(3);
            return new paymentprefBean (P_ID, Meth, null, CRE, 0); 
        }         
       return null; 
    }
        
       
    //Add a supplier into the db
    public void addPayment (String Payment_ID, String Payment_DATE, String Payment_METHOD, String Creditcard, int Amount) throws SQLException {
        st.executeUpdate("INSERT INTO sql12346043.PAYMENTDB" + "VALUES ("+Payment_ID+", "+Payment_DATE+", "+Payment_METHOD+", "+Creditcard+", "+Amount+")");
    }
    //Update a Suppliers information
    public void updatePayment(String Payment_ID, String Payment_DATE, String Payment_METHOD, String Creditcard, int Amount) throws SQLException {       
       //code for update-operation   



       st.executeUpdate("UPDATE INTO sql12346043.PAYMENTDB SET Payment_ID ="+Payment_ID+", SET Payment_DATE ="+Payment_DATE+", SET Payment_METHOD ="+Payment_METHOD+", SET Creditcard ="+Creditcard+", SET Amount ="+Amount+",");  

    }   
    //delete a supplier from db
    public void deletePayment(String Payment_ID) throws SQLException{
        st.executeUpdate("DELETE FROM sql12346043.PAYMENTDB WHERE PAYMENTID ='"+Payment_ID+"'");
  
    }

    
}