/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author willi
 */
public class CustomerBean implements Serializable{
    private String password, name,email,title,address,phone;
    private Date joined, DOB;
    int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    
    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public Date getJoined() {
        return joined;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDOB() {
        return DOB;
    }
    
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer:"+name+", "+email+", "+password+" joined "+joined;
    }


    
    
}
