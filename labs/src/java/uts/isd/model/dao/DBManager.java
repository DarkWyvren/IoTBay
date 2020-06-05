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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.text.DateFormatter;
import uts.isd.model.CustomerAccessLogBean;

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
       //setup the select sql query string       
       //execute this query using the statement field       
       //add the results to a ResultSet       
       //search the ResultSet for a user using the parameters               
       return null;   
    }   
    
    
    
    //Add a user-data into the database   
    public void addCustomer(CustomerBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");
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
      st.executeUpdate("INSERT INTO APP.CUSTOMERDB(Email, Password,FullName,DOB,Address,Phone,Title)  VALUES("+values+")");   
      cb.setId(findCustomer(cb.getEmail(),cb.getPassword()).getId());

    }
    public CustomerAccessLogBean addCustomerLoginRecord(CustomerBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyy");
      SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
      java.util.Date d =  new java.util.Date();
      String values=
              ""
              +cb.getId()+",'"
              +dateformat.format(d)+"','"
              +timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate("INSERT INTO APP.CUSTOMER_SESSION(Customer_ID, LOGGEDIN_DATE,LOGGEDIN_TIME)  VALUES("+values+")");   
      CustomerAccessLogBean cab = new CustomerAccessLogBean();
      cab.setCustomer(cb);
      cab.setLoggedin(d);
      return cab;
    }
    
    public void endCustomerLoginRecord(CustomerAccessLogBean cb) throws SQLException {                   
//code for add-operation       
//VALUES(0,'pepe@gmail.com','password','Pai pei','12/17/1947','123 Hujianyan St, HongDoui, Singapore',35702572,'Mr');
      SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyy");
      SimpleDateFormat timeformat = new SimpleDateFormat("H:m:s");
      java.util.Date d =  new java.util.Date();
      String values=
              "LOGGEDOUT_DATE = '"+dateformat.format(d)+"',"+
              "LOGGEDOUT_TIME = '"+timeformat.format(d)+"'"
              ;
      System.out.println(values);
      st.executeUpdate(
                "UPDATE APP.CUSTOMER_SESSION SET "+values+" "
                        + "WHERE"
                        + " Customer_ID = "+cb.getCustomerid()+
                          " AND LOGGEDIN_TIME = '"+timeformat.format(cb.getLoggedin())+"'"+
                          " AND LOGGEDIN_DATE = '"+dateformat.format(cb.getLoggedin())+"'" );   
      cb.setLoggedout(d);
    }
    
    public ArrayList<CustomerAccessLogBean> listCustomerLoginRecord(int cid) throws SQLException {                   
        ArrayList<CustomerAccessLogBean> result = new ArrayList<>();
      String query = "SELECT * FROM APP.CUSTOMER_SESSION WHERE  Customer_ID="+cid;
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
             CustomerAccessLogBean cb = new CustomerAccessLogBean();
                cb.setCustomerid(cid);
                cb.setLoggedin(Date.valueOf(rs.getString(2)));
                cb.setLoggedout(rs.getString(4)==null?cb.getLoggedin():Date.valueOf(rs.getString(4)));
                result.add(cb);
        }
        return result;
    }

    //update a user details in the database   
    public void updateCustomer(CustomerBean cb) throws SQLException {       
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");
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
        st.executeUpdate("UPDATE APP.CUSTOMERDB SET "+values+" WHERE Customer_ID ="+cb.getId());   

    }       

    //delete a user from the database   
    public void deleteCustomer(String email) throws SQLException{       
       st.executeUpdate("DELETE FROM APP.CUSTOMERDB WHERE Email='"+email+"'");
    }


}