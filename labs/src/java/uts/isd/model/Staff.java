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
    private String FullName;
    private String Address;
    private String password;
    private String Pos;
    private int Status;
    
    public Staff(int Id, String Email, String FullName, String Address, String Position, int Status)
            {
                this.Id = Id;
                this.Email = Email;
                this.Address = Address;
                this.FullName = FullName;
                this.Pos = Position;
                this.Status = Status;
            }
    
    public Staff() {
        Email = "";
        FullName = "";
        Pos = "";
        Address = "";
        password="";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    
    public String getPosition() {
        return Pos;
    }

    public void setPosition(String Position) {
        this.Pos = Position;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
}
