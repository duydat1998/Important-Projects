/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_Order;

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
public class tbl_OrderDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public int insertOrder(Integer total, String userId, float totalAmount) throws NamingException, SQLException {
        int generatedKey = 0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert Into tbl_Order (total, userId, totalAmount) values (?,?,?)";
                stm = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                stm.setInt(1, total);
                stm.setString(2, userId);
                stm.setFloat(3, totalAmount);
                int count = stm.executeUpdate();
                if(count>0){
                    rs=stm.getGeneratedKeys();
                    if(rs.next()){
                        generatedKey=rs.getInt(1);
                    }
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return generatedKey;
    }
}
