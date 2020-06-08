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
public class Product implements Serializable{
    
    private int productid;
    private String name;
    private double price;
    private String category;
    private int supplier;
    private int quantity;
           
    public Product (int P_ID, String PNAME, Double PPrice, String PCATEGORY, int SUP_ID, int PQUANT) {
        this.productid = P_ID;
        this.name=PNAME;
        this.price=PPrice;
        this.category=PCATEGORY;
        this.supplier=SUP_ID;
        this.quantity=PQUANT;
        
    }
 
  public Product () {
      productid = 0;
      name = "";
      price = 0;
      category = "";
      supplier = 0;
      quantity = 0;
             
  }
  
    public int getID() {
            return productid;
    }
    
    public void setID(int id) {
        this.productid = productid;
    }
    
    public String getName() {
        return name;
    }
            
    
    public void setName (String name){
        this.name = name;
    }
    
    
    public double getPrice() {
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
    
    
    public int getSupplier() {
        return supplier;
    }
    
    
    public void setSupplier(int supplier){
        this.supplier = supplier;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    
    
    
    
    
   @Override
   public String toString() {
    return "Product:" +productid+", "+name+", "+price+", "+category+ ", "+supplier+", "+quantity;
   }
   
    
}

    
