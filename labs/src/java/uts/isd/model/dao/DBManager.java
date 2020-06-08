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
import uts.isd.model.ProductBean;
import uts.isd.model.Supplier;
import uts.isd.model.OrderBean;
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
        String query = "SELECT * FROM sql12346043.PRODUCTDB WHERE  Product_ID='"+Product_ID;
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
            } return product;     
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
     public Supplier findSupplier(String CompanyName, String CompanyType) throws SQLException {   
        String query = "SELECT * FROM sql12346043.SUPPLIERDB WHERE  SupName='"+CompanyName+"' AND Password = '"+CompanyType+"'";

        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            String C_Name = rs.getString(2);
            String C_Type = rs.getString(4);
            

            if(C_Name.equals(CompanyName) && C_Type.equals(CompanyType) ){ 
                int S_ID = rs.getInt(1);               
                String C_Address = rs.getString(3);
                String C_Email = rs.getString(5);
                int C_Status = rs.getInt(6);

              //  return new Supplier (C_Name, C_Address, C_Type, C_Email, C_Status); 


                System.out.println("Company Name: " +C_Name);
                return new Supplier (S_ID, C_Name, C_Address, C_Type, C_Email, C_Status); 

            }
        }         
       return null;   
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
              "SupEmail = '"+sb.getCompanyEmail()+"'"
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
    

    
    
//Order Management [MVC]
    //Find all orders based on Date_Of_Order
    /*public OrderBean findOrder(String Date_Of_Order) throws SQLException {  
        return findOrder(Date_Of_Order, "");
    }*/
    //Find the specific order using Date_Of_Order and Order_ID
    public OrderBean findOrder(String Date_Of_Order, int Order_ID) throws SQLException {   
        String query = "SELECT * FROM sql12346043.ORDERDB WHERE Date_Of_Order='"+Date_Of_Order+"' AND Order_ID = '"+Order_ID;
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String order_date = rs.getString(3);
            int ord_id = rs.getInt(1);
            System.out.println(order_date+","+ord_id);
            if(order_date.equals(Date_Of_Order)&& ord_id == (Order_ID)){
                OrderBean ob = new OrderBean();
                ob.setOrderId(ord_id);
                ob.setCustomerId(rs.getInt(2));
                
                String[] dt = rs.getString(3).split("/");
                System.out.println(Arrays.toString(dt));
                ob.setDOO(Date.valueOf(rs.getString(3)));
                
                ob.setShippingAddress(rs.getString(4));
                ob.setStatus(rs.getString(5));
                ob.setProductId(rs.getInt(6));
                ob.setProductName(rs.getString(7));
                ob.setProductPrice(rs.getDouble(8));
                ob.setProductQuanity(rs.getInt(9));
                ob.setTotalPrice(rs.getDouble(10));

                return ob;
            }
        }            
        return null;   
    }
    
    //Add a order-data into the database   
    public void addOrder(OrderBean ob) throws SQLException {                   
    //code for add-operation    
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String values=
              "'"
              +ob.getCustomerId()+"','"
              +format.format(ob.getDOO())+"','"
              +ob.getShippingAddress()+"','"
              +ob.getStatus()+"','"
              +ob.getProductId()+"','"
              +ob.getProductName()+"','"
              +ob.getProductPrice()+"','"
              +ob.getProductQuanity()+"','"
              +ob.getTotalPrice()+"'"
              ;
        System.out.println(values);
        st.executeUpdate("INSERT INTO sql12346043.ORDERDB(Customer_ID, Date_Of_Order, Address, Status, Product_ID, Product_Quanity, Total_Price)  VALUES("+values+")");   
        ob.setOrderId(findOrder(format.format(ob.getDOO()),ob.getOrderId()).getOrderId());  
    }
    
    //update a order details in the database   

    public void updateOrder(OrderBean ob) throws SQLException {       
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String values=
              "Customer_ID = '"+ob.getCustomerId()+"',"+
              "Date_Of_Order = '"+format.format(ob.getDOO())+"',"+
              "Address = '"+ob.getShippingAddress()+"',"+
              "Status = '"+format.format(ob.getStatus())+"',"+
              "Product_ID = '"+ob.getProductId()+"',"+
              "Product_Name = '"+ob.getProductName()+"',"+
              "Product_Price = '"+ob.getProductPrice()+"',"+
              "Product_Quanity = '"+ob.getProductQuanity()+"'"+
              "Total_Price = '"+ob.getTotalPrice()+"'"
              ;
        System.out.println(values);
        st.executeUpdate("UPDATE sql12346043.ORDERDB SET "+values+" WHERE Order_ID ="+ob.getOrderId());
    } 
    
    //delete a order from the database   
    public void deleteOrder(int Order_ID) throws SQLException{       
       //code for delete-operation   
       st.executeUpdate("DELETE FROM sql12346043.ORDERDB WHERE Order_ID ='"+Order_ID+"'");
    }

    
    
    
    
    
    
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