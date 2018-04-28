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
    
    public tbl_UserDAO() {
    }

    public int checkLogin(String userId, String password) throws SQLException, NamingException {
        int role = -1;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                //2. Tao cau lenh truy van
                String sql = "Select role From tbl_User Where userId=? AND password=?";
                //3. Tao statement
                stm = con.prepareStatement(sql);
                //4. Truyen tham so
                stm.setString(1, userId);
                stm.setString(2, password);
                //5. Thuc thi cau lenh truy van
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }//end if con==null
        } finally {
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
        return role;
    }
}
