
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author mood35-Laptop
 */
public class Supplier implements Serializable {
    private String ContactName;
    private String CompanyAddress;
    private String CompanyEmail;
    private String CompanyType;
    private int ConNumber;
    private int Status;

   /* public Supplier (String ContactName, String CompanyAddress,int ConNumber, String CompanyType, String CompanyEmail, int Status) {
        this.ContactName = ContactName;
        this.CompanyAddress = CompanyAddress;
        this.CompanyEmail = CompanyEmail;
        this.CompanyType = CompanyType;
        this.ConNumber = ConNumber;
        this.Status = Status;
    }
*/
    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String CompanyAddress) {
        this.CompanyAddress = CompanyAddress;
    }

    public String getCompanyemail() {
        return CompanyEmail;
    }

    public void setCompanyemail(String Companyemail) {
        this.CompanyEmail = Companyemail;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String CompanyType) {
        this.CompanyType = CompanyType;
    }

    public int getConNumber() {
        return ConNumber;
    }

    public void setConNumber(int ConNumber) {
        this.ConNumber = ConNumber;
    }   

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

}


