/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_customer;

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
public class tbl_customerDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public tbl_customerDAO() {
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

    public String checkLogin(String custId, String password) throws SQLException, NamingException {
        String custName = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select custName From tbl_customer Where custID=? AND password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, custId);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    custName = rs.getString("custName");
                }
            }
        } finally {
            closeTransport();
        }
        return custName;
    }

    public tbl_customerDTO getCustomerById(String custID) throws SQLException, NamingException {
        tbl_customerDTO dto = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_customer Where custID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, custID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String custName = rs.getString("custName");
                    dto = new tbl_customerDTO(custID, null, custName, null, null, address, phone, 0);
                }
            }
        } finally {
            closeTransport();
        }
        return dto;
    }

    public boolean insertCustomer(tbl_customerDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_customer (custID,password,custName,lastName,middleName,address,phone,custLevel) values(?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getCustID());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getCustName());
                stm.setString(4, dto.getLastName());
                stm.setString(5, dto.getMiddleName());
                stm.setString(6, dto.getAddress());
                stm.setString(7, dto.getPhone());
                stm.setInt(8, dto.getCustLevel());
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
