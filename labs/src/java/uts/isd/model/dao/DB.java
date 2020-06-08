/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;

/**
 *
 * @author willi
 */
public class DB {
    protected String URL = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12346043?zeroDateTimeBehavior=convertToNull";	
    protected String db = "sql12346043";
    protected String dbuser=  "sql12346043";
    protected String dbpass = "nZFipmZBxp";
    protected String driver = "com.mysql.jdbc.Driver";
    protected Connection conn;
}
