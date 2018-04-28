/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_orderDetailDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private List<tbl_orderDetailDTO> listOrderDetail = null;

    public tbl_orderDetailDAO() {
    }

    public void closeTransport() throws SQLException {
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

    public void searchByOrderID(String orderID) throws SQLException, NamingException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_orderDetail where orderID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String productID = rs.getString("productID");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    float total = rs.getFloat("total");
                    tbl_orderDetailDTO dto = new tbl_orderDetailDTO(id, quantity, productID, orderID, total, unitPrice);
                    if (listOrderDetail == null) {
                        listOrderDetail = new ArrayList<>();
                    }
                    listOrderDetail.add(dto);
                }
            }
        } finally {
            closeTransport();
        }
    }

    /**
     * @return the listOrderDetail
     */
    public List<tbl_orderDetailDTO> getListOrderDetail() {
        return listOrderDetail;
    }

    /**
     * @param listOrderDetail the listOrderDetail to set
     */
    public void setListOrderDetail(List<tbl_orderDetailDTO> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

}
