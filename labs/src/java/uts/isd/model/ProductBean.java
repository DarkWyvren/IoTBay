/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author antho
 */
public class ProductBean implements Serializable{
    private String id, name, price, category, supplier;
 
  
    public String getID() {
            return id;
    }
    
    public void setID(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
            
    
    public void setName (String name){
        this.name = name;
    }
    
    
    public String getPrice () {
        return price;
    }
    
    
    public void setPrice(Double Price) {
        this.price = price;
    }
    
    
    public String getCategory() {
        return category;
    }
    
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    
    public String getSupplier() {
        return supplier;
    }
    
    
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }
    
    
    
    
    
    
    
    
    
   @Override
   public String toString() {
    return "Product:" +id+", "+name+", "+price+", "+category+ ", "+supplier;
   }
   
    
}

    
