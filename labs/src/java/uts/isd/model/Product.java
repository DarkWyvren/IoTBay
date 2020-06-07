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
<<<<<<< Updated upstream:labs/src/java/uts/isd/model/ProductBean.java
public class ProductBean implements Serializable{
    private String id, name, price, category, supplier;
 
=======
public class Product implements Serializable{
    
    private int productid;
    private String name;
    private double price;
    private String category;
    private int supplier;
           
 
  public Product () {
      productid = 0;
      name = "";
      price = 0;
      category = "";
      supplier = 0;
             
  }

    public Product(int Product_ID, String ProductName, double price, String Category, int SupplierID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> Stashed changes:labs/src/java/uts/isd/model/Product.java
  
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

    
