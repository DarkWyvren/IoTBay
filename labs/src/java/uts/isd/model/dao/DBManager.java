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
import uts.isd.model.Supplier;
import java.sql.*;
import java.util.Arrays;

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
        String query = "SELECT * FROM APP.CUSTOMERDB WHERE  Email='"+email+"'"+ (password.length()>0? " AND Password = '"+password+"'":"");
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
                
                String[] dt = rs.getString(5).split("/");
                System.out.println(Arrays.toString(dt));
                
                cb.setDOB(Date.valueOf(rs.getString(5)));
                return cb;
            }
        }
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters               
       return null;   
    }
    //Add a user-data into the database   
    public void addCustomer(String email, String name, String password, String gender, String favcol) throws SQLException {                   
//code for add-operation       
      st.executeUpdate("sql query");   

    }

    //update a user details in the database   
    public void updateCustomer( String email, String name, String password, String gender, String favcol) throws SQLException {       
       //code for update-operation   

    }       

    //delete a user from the database   
    public void deleteCustomer(String email) throws SQLException{       
       //code for delete-operation   

    }

    
    
    
    //SUPPLIER INFO
    //can view supplier info
     public void showSupplier(String ContactName, String CompanyAddress,int ConNumber, String CompanyType, String CompanyEmail, int Status) throws SQLException{
        String query = "SELECT FROM * APP.SUPPLIERDB";
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
        String query = "SELECT * FROM APP.SUPPLIERDB WHERE  CONTACTNAME='"+ContactName+"'"+ (" AND EMAILADDRESS = '"+CompanyEmail+"'");
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            String Com_Name = rs.getString(2);
            String Com_Email = rs.getString(3);
            System.out.println(Com_Name+","+Com_Email);
            if(Com_Name.equals(ContactName)&& Com_Email.equals(CompanyEmail)){
                Supplier sb = new Supplier();
                sb.setContactName(Com_Name);
                sb.setCompanyemail(Com_Email);
                
                return sb;
            }
        }
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters               
       return null;   
    }*/
    //Add a supplier into the db
    public void addSupplier (String ContactName, String CompanyAddress,int ConNumber, String CompanyType, String CompanyEmail, int Status) throws SQLException {
        st.executeUpdate("INSERT INTO SUPPLIERDB" + "VALUES ("+ContactName+", "+CompanyAddress+", "+ConNumber+", "+CompanyType+", "+CompanyEmail+", "+Status+")");
    }
    //Update a Suppliers information
    public void updateSupplier (String ContactName, String CompanyAddress,int ConNumber, String CompanyType, String CompanyEmail, int Status) throws SQLException {
        st.executeUpdate("INSERT INTO SUPPLIERDB SET CONTACTNAME ="+ContactName+", SET COMADDRESS  "+CompanyAddress+", SET COMNUMBER "+ConNumber+", SET COMTYPE "+CompanyType+", SET STATUS "+Status+" WHERE EMAILADDRESS ='"+CompanyEmail+"'");
    }
    //delete a supplier from db
    public void deleteSupplier(String CompanyEmail) throws SQLException{
        st.executeUpdate("DELETE FROM APP.SUPPLIER WHERE EMAILADDRESS ='"+CompanyEmail+"'");
  
    }
    
    public Arraylist<Supplier> fetchSupplierList() throws SQLException{
        
    }
}