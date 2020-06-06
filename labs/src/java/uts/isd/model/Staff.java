/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author Danny16
 */
public class Staff implements Serializable
{
    private int Id;
    private String Email;
    private String Password;
    private String FullName;
   // private String Dob;
    private String Address;
    private int Phone;
    private String Position;
    
    public Staff(int Id, String Email, String Password, String FullName, String Address, int Phone, String Position)
            {
                this.Id = Id;
                this.Email = Email;
                this.Address = Address;
              //  this.Dob = Dob;
                this.Password = Password;
                this.Phone = Phone;
                this.FullName = FullName;
                this.Position = Position;
            }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }
    
    
}
