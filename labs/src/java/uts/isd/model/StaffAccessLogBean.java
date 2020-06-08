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
public class StaffAccessLogBean {
    private Staff staff;
    private int customerid;
    
    private Date loggedin;
    
    private Date loggedout;

    public StaffAccessLogBean() {
    }

    public void setLoggedin(Date loggedin) {
        this.loggedin = loggedin;
    }

    public void setLoggedout(Date loggedout) {
        this.loggedout = loggedout;
    }

    public void setStaff(Staff customer) {
        this.staff = customer;
        this.customerid = customer.getId();
    }

    public Date getLoggedin() {
        return loggedin;
    }

    public Date getLoggedout() {
        return loggedout;
    }

    public Staff getStaff() {
        return staff;
    }

    public int getStaffid() {
        return customerid;
    }

    public void setStaffid(int customerid) {
        this.customerid = customerid;
    }
    
    
    
    
}
