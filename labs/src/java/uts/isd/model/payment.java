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
public class payment implements Serializable{
private String Payment_ID;
private String Payment_METHOD;
private String Payment_DATE;
private String Creditcard_Details;
private int Payment_Amount;

    public payment(String Payment_ID, String Payment_METHOD, String Payment_DATE, String Creditcard_Details, int Payment_Amount) {
        this.Payment_ID = Payment_ID;
        this.Payment_METHOD = Payment_METHOD;
        this.Payment_DATE = Payment_DATE;
        this.Creditcard_Details = Creditcard_Details;
        this.Payment_Amount = Payment_Amount;
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

    public String getPayment_DATE() {
        return Payment_DATE;
    }

    public void setPayment_DATE(String Payment_DATE) {
        this.Payment_DATE = Payment_DATE;
    }

    public String getCreditcard_Details() {
        return Creditcard_Details;
    }

    public void setCreditcard_Details(String Creditcard_Details) {
        this.Creditcard_Details = Creditcard_Details;
    }

    public int getPayment_Amount() {
        return Payment_Amount;
    }

    public void setPayment_Amount(int Payment_Amount) {
        this.Payment_Amount = Payment_Amount;
    }

}   
