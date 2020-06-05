/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

/**
 *
 * @author Danny16
 */
public class Staff {
    private int Id;
    private String Email;
    private String Password;
    private String FullName;
    private String Dob;
    private String Address;
    private int Phone;
    
    public Staff(int Id, String Email, String Password, String FullName, String Dob, String Address, int Phone)
            {
                this.Id = Id;
                this.Email = Email;
                this.Address = Address;
                this.Dob = Dob;
                this.Password = Password;
                this.Phone = Phone;
                this.FullName = FullName;
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

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
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
