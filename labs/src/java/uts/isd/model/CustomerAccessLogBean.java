/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.Date;

/**
 *
 * @author willi
 */
public class CustomerAccessLogBean {
    private CustomerBean customer;
    private int customerid;
    
    private Date loggedin;
    
    private Date loggedout;

    public CustomerAccessLogBean() {
    }

    public void setLoggedin(Date loggedin) {
        this.loggedin = loggedin;
    }

    public void setLoggedout(Date loggedout) {
        this.loggedout = loggedout;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
        this.customerid = customer.getId();
    }

    public Date getLoggedin() {
        return loggedin;
    }

    public Date getLoggedout() {
        return loggedout;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    
    
    
    
}
