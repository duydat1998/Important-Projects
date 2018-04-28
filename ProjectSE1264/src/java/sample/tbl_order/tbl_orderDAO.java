/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class tbl_orderDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private List<tbl_orderDTO> listOrder = null;

    public tbl_orderDAO() {
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

    /**
     * @return the listOrder
     */
    public List<tbl_orderDTO> getListOrder() {
        return listOrder;
    }

    /**
     * @param listOrder the listOrder to set
     */
    public void setListOrder(List<tbl_orderDTO> listOrder) {
        this.listOrder = listOrder;
    }

    public void searchFromDateToDate(Date from, Date to, boolean isDeliver) throws SQLException, NamingException {
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_order where (orderDate BETWEEN ? AND ?) AND isDeliver=?";
                stm = con.prepareStatement(sql);
                stm.setDate(1, from);
                stm.setDate(2, to);
                stm.setBoolean(3, isDeliver);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String custID = rs.getString("custID");
                    String reason = rs.getString("Reason");
                    Date orderDate = rs.getDate("orderDate");
                    boolean deliver = rs.getBoolean("isDeliver");
                    float total = rs.getFloat("total");
                    tbl_orderDTO dto = new tbl_orderDTO(orderID, custID, reason, orderDate, deliver, total);
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    listOrder.add(dto);
                    System.out.println("haha");
                }
            }
        } finally {
            closeTransport();
        }

    }

    public tbl_orderDTO getOrderById(String orderID) throws SQLException, NamingException {
        tbl_orderDTO dto = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_order where orderID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String custID = rs.getString("custID");
                    String reason = rs.getString("Reason");
                    Date orderDate = rs.getDate("orderDate");
                    boolean deliver = rs.getBoolean("isDeliver");
                    float total = rs.getFloat("total");
                    dto = new tbl_orderDTO(orderID, custID, reason, orderDate, deliver, total);
                }
            }
        } finally {
            closeTransport();
        }
        return dto;
    }

    public boolean changeDeliverState(String orderID, boolean isDeliver) throws SQLException, NamingException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tbl_order SET isDeliver=? where orderID=?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, isDeliver);
                stm.setString(2, orderID);
                int count = stm.executeUpdate();
                if (count > 0) {
                    result = true;
                }
            }
        } finally {
            closeTransport();
        }
        return result;
    }
}
