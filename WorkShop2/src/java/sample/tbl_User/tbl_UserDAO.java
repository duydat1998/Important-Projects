/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_UserDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeTransport() throws SQLException{
         if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
    }
    public tbl_UserDAO() {
    }

    public tbl_UserDTO checkLogin(String userId, int password) throws SQLException, NamingException {
        tbl_UserDTO dto= null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_User Where userId=? AND password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setInt(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName=rs.getString("fullName");
                    int role = rs.getInt("role");
                    dto=new tbl_UserDTO(userId, fullName, password, role);
                }
            }
        } finally {
           closeTransport();
        }
        return dto;
    }
}
