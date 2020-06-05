/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.dao.DBManager;

/**
 *
 * @author antho
 */
public class ValidProduct {
    private static ArrayList<ProductBean> probeanlist = new ArrayList<>();
    
    public static String[] getAllProductIDs(){
        String[] out= new String[probeanlist.size()];
        for(int i =0;i<probeanlist.size();i++){
            out[i]=probeanlist.get(i).getID();
        }
        return out;
    }
    
    
    public static String createProduct(ProductBean pb) {
        for(ProductBean pass:probeanlist){
            if(pass==pb) {
                return "Product already exists";
            }
        
            if(pb.getID().equals(pass.getID())){
                return "A Product with this ID already exists";
            }
        }
        if(pb.getName().isEmpty()){
            return "Please enter a Name";
        }
        
        if(pb.getPrice().isEmpty()){
            return "Please enter a Price for the product";
        }
        
        if(pb.getCategory().isEmpty()) {
            return "Please enter a Category for the product";
        }
        
        if(pb.getSupplier().isEmpty()){
            return "Does not match a supplier ID";
        }
        
        probeanlist.add(pb);
        return"Product Added";
    }
    
}
