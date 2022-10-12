/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    private static Connection conn;
    static
    {        
    try
    {
        Class.forName("oracle.jdbc.OracleDriver");
        System.out.println("Driver successfully loaded");
        conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-GNRTIE3:1521/XE","evoting","evoting");
        System.out.println("Connected successfully to the DB");
    }
    catch(Exception e)
    {
        System.out.println("Exception In Connecting To DB:"+e);
    }
}
    public static Connection getConnection()
    {
        return conn;
    }
}    
