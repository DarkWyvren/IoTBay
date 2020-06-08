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
 * @author 10847
 */
public class paymentprefBean implements Serializable{
private String Payment_ID;
private String Payment_METHOD;
private int cusid;

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

private String Creditcard_Details;

    public paymentprefBean(String Payment_ID, String Payment_METHOD, String Payment_DATE, String Creditcard_Details, int Payment_Amount) {
        this.Payment_ID = Payment_ID;
        this.Payment_METHOD = Payment_METHOD;
        this.Creditcard_Details = Creditcard_Details;
    }

    public paymentprefBean() {
        
    }

    public String getPayment_ID() {
        return Payment_ID;
    }

    public void setPayment_ID(String Payment_ID) {
        this.Payment_ID = Payment_ID;
    }

    public String getPayment_METHOD() {
        return Payment_METHOD;
    }

    public void setPayment_METHOD(String Payment_METHOD) {
        this.Payment_METHOD = Payment_METHOD;
    }

    public String getCreditcard_Details() {
        return Creditcard_Details;
    }

    public void setCreditcard_Details(String Creditcard_Details) {
        this.Creditcard_Details = Creditcard_Details;
    }



}   
