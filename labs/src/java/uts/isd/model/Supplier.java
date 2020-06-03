
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author mood35-Laptop
 */
public class Supplier implements Serializable {
    private String ContactName;
    private String CompanyAddress;
    private String Companyemail;
    private String CompanyType;
    private int ConNumber;

    public Supplier(String ContactName, String CompanyAddress, String Companyemail, String CompanyType, int ConNumber) {
        this.ContactName = ContactName;
        this.CompanyAddress = CompanyAddress;
        this.Companyemail = Companyemail;
        this.CompanyType = CompanyType;
        this.ConNumber = ConNumber;
    }

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
        return Companyemail;
    }

    public void setCompanyemail(String Companyemail) {
        this.Companyemail = Companyemail;
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
}
