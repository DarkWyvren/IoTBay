/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author willi
 */

public class AccountTracker {
    private static ArrayList<CustomerBean> csbeanlist = new ArrayList<>();
    private static ArrayList<CustomerBean> loggedin = new ArrayList<>();
    
    
    public static String[] getAllEmails(){
        String[] out = new String[csbeanlist.size()];
        for(int i =0;i<csbeanlist.size();i++){
            out[i]=csbeanlist.get(i).getEmail();
        }
        return out;
    }
    
    public static String registerAccount(CustomerBean cb){
        for(CustomerBean pass:csbeanlist){
            if(pass==cb){
                return "Account already exists";
            }
            
            if(cb.getEmail().equals(pass.getEmail())){
                return "An account with that email already exists";
            }
        }
        if(cb.getPassword().length()<5){
            return "Password needs to be 5 characters or longer";
        }
        cb.setJoined(new Date());
        csbeanlist.add(cb);
        return "OK";
    }
    
    public static void login(CustomerBean cb){
        if(cb==null){
            return;
        }
        loggedin.add(cb);
        for(CustomerBean cust:csbeanlist){
            if(cb.getEmail().equals(cust.getEmail())){
                return;
            }
        }
        csbeanlist.add(cb);
    }
    
    public static void logout(CustomerBean cb){
        for(int i =0;i<loggedin.size();i++){
            if(cb.getEmail().equals(loggedin.get(i))){
                loggedin.remove(i);
                return;
            }
        }
    }
    
    public static boolean isLoggedIn(String email){
        
        for(CustomerBean cust:loggedin){
            if(cust.getEmail()!=null&&cust.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    
    public static CustomerBean getCustomerByEmail(DBManager db , String email){
        for(CustomerBean cust:csbeanlist){
            if(cust.getEmail().equals(email)){
                return cust;
            }
        }
        for(CustomerBean cust:loggedin){
            if(cust.getEmail().equals(email)){
                return cust;
            }
        }
        try{
        db.findCustomer(email);
        }catch(Exception e){
            Logger.getLogger(AccountTracker.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        //finally search db
        
        return null;
    }
    private static boolean isValidLogin(String email, String pass){
        for(CustomerBean cust:csbeanlist){
            if(cust.getEmail().equals(email) && cust.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
    public static boolean isValidLogin(DBManager db , String email, String pass){
        if(isValidLogin(email,pass)){return true;}
        
        try {
            return db.findCustomer(email, pass)!=null;
        } catch (SQLException ex) {
            Logger.getLogger(AccountTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
