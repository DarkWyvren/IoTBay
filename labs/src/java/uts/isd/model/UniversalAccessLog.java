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
public class UniversalAccessLog {
     private Date loggedin;
    
    private Date loggedout;

    public Date getLoggedin() {
        return loggedin;
    }

    public void setLoggedout(Date loggedout) {
        this.loggedout = loggedout;
    }

    public Date getLoggedout() {
        return loggedout;
    }

    public void setLoggedin(Date loggedin) {
        this.loggedin = loggedin;
    }

    public UniversalAccessLog(Date loggedin, Date loggedout) {
        this.loggedin = loggedin;
        this.loggedout = loggedout;
    }
    
}
