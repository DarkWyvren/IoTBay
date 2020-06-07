
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author mood35-Laptop
 */
public class Supplier implements Serializable {
    private int SupplierID;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyType;
    private String CompanyEmail;
    private int CompanyStatus;
    

    public Supplier (int S_ID, String CNAME, String CADDRESS, String CTYPE, String CEMAIL, int CSTATUS) {
        this.SupplierID = S_ID;
        this.CompanyName = CNAME;
        this.CompanyAddress = CADDRESS;
        this.CompanyType= CTYPE;
        this.CompanyEmail= CEMAIL;
        this.CompanyStatus = CSTATUS;
    }
    
    public Supplier (){
      SupplierID = 0;
      CompanyName = "";
      CompanyAddress = "";
      CompanyType = "";
      CompanyEmail = "";
      CompanyStatus = -1;
        
    }
    
    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }


    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String CompanyAddress) {
        this.CompanyAddress = CompanyAddress;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String CompanyType) {
        this.CompanyType = CompanyType;
    }

    public String getCompanyEmail() {
        return CompanyEmail;
    }

    public void setCompanyEmail(String CompanyEmail) {
        this.CompanyEmail = CompanyEmail;
    }

    public int getCompanyStatus() {
        return CompanyStatus;
    }

    public void setCompanyStatus(int CompanyStatus) {
        this.CompanyStatus = CompanyStatus;
    }

    
}