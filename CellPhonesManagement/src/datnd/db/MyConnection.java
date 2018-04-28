/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datnd.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nguyen Duy Dat
 */
public class MyConnection {
    //dung static de du lieu nam tren stack, tang performance
    public static Connection getMyConnection() throws Exception{
        Connection conn=null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn=DriverManager.getConnection("jdbc:sqlserver://localhost:2011;databaseName=CellPhonesDatabase","sa","");
        return conn;
    }
}
