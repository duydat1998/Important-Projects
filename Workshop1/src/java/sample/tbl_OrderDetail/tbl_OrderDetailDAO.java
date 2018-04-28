/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_OrderDetail;

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
public class tbl_OrderDetailDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement stm= null;
    private ResultSet rs=null;
    public boolean insertOrderDetail(String mobileId, Integer quantity, Integer orderID) throws NamingException, SQLException{
        boolean result=false;
        try {
            con=DBUtils.makeConnection();
            if(con!=null){
                String sql="Insert Into tbl_OrderDetail(mobileId,quantity,orderID) values(?,?,?)";
                stm=con.prepareStatement(sql);
                stm.setString(1, mobileId);
                stm.setInt(2, quantity);
                stm.setInt(3, orderID);
                int count = stm.executeUpdate();
                if(count>0)
                    result=true;
            }
        } finally {
            if(stm!= null){
                stm.close();
            }
            if(con!=null)
                con.close();
        }
        return result;
    }
}
