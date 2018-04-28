/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_Mobile;

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
public class tbl_MobileDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public tbl_MobileDTO getMobileById(String Id) throws NamingException, SQLException {
        tbl_MobileDTO result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_Mobile where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    Integer year = rs.getInt("yearOfProduction");
                    Integer quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    result = new tbl_MobileDTO(mobileId, description, mobileName, price, year, quantity, notSale);
                }
            }
        } finally {
            closeTransport();
        }

        return result;
    }
    
    public float getPriceById(String Id) throws NamingException, SQLException {
        float price=0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select price from tbl_Mobile where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    price = rs.getFloat("price");
                }
            }
        } finally {
            closeTransport();
        }

        return price;
    }

    public List<tbl_MobileDTO> searchMobileById(String search) throws NamingException, SQLException {
        List<tbl_MobileDTO> result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_Mobile where mobileId LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    System.out.println(price+"$");
                    String mobileName = rs.getString("mobileName");
                    Integer year = rs.getInt("yearOfProduction");
                    System.out.println("year"+ year);
                    Integer quantity = rs.getInt("quantity");
                    System.out.println("quantity"+ quantity);
                    boolean notSale = rs.getBoolean("notSale");
                    tbl_MobileDTO dto = new tbl_MobileDTO(mobileId, description, mobileName, price, year, quantity, notSale);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
                }
            }
        } finally {
            closeTransport();
        }

        return result;
    }

    public List<tbl_MobileDTO> searchMobileByName(String search) throws NamingException, SQLException {
        List<tbl_MobileDTO> result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_Mobile where mobileName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    Float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    Integer year = rs.getInt("yearOfProduction");
                    Integer quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    tbl_MobileDTO dto = new tbl_MobileDTO(mobileId, description, mobileName, price, year, quantity, notSale);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
                }
            }
        } finally {
            closeTransport();
        }

        return result;
    }

    public List<tbl_MobileDTO> searchMobileByPriceRange(float min, float max, boolean notSale) throws NamingException, SQLException {
        List<tbl_MobileDTO> result = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from tbl_Mobile where (price BETWEEN ? AND ?) AND notsale=?";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, min);
                stm.setFloat(2, max);
                stm.setBoolean(3, notSale);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    //boolean notSale=rs.getBoolean("notSale");
                    tbl_MobileDTO dto = new tbl_MobileDTO(mobileId, description, mobileName, price, year, quantity, notSale);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
                }
            }
        } finally {
            closeTransport();
        }
        return result;
    }

    public boolean insertNewMobile(String mobileId, String description, String mobileName, Float price, Integer yearOfProduction, Integer quantity, boolean notSale)
            throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert into tbl_Mobile(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) values (?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, mobileId);
                stm.setString(2, description);
                if(price == null){
                    stm.setNull(3, java.sql.Types.FLOAT);
                } else{
                    stm.setFloat(3, price);
                }
                stm.setString(4, mobileName);
                if(yearOfProduction == null){
                    stm.setNull(5, java.sql.Types.INTEGER);
                } else{
                    stm.setInt(5, yearOfProduction);
                }
                if(quantity == null){
                    stm.setNull(6, java.sql.Types.INTEGER);
                } else{
                    stm.setInt(6, quantity);
                }
                stm.setBoolean(7, notSale);
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

    public boolean deletePk(String mobileId) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Delete from tbl_Mobile where mobileId=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, mobileId);
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

    public boolean updateRecord(String mobileId, String description, Float price, Integer quantity, boolean notSale) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tbl_Mobile set description=?, price=?, quantity=?, notSale=? where mobileId=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, description);
                if(price == null){
                    stm.setNull(2, java.sql.Types.FLOAT);
                } else{
                    stm.setFloat(2, price);
                }
                if(quantity == null){
                    stm.setNull(3, java.sql.Types.INTEGER);
                } else{
                    stm.setInt(3, quantity);
                }
                stm.setBoolean(4, notSale);
                stm.setString(5, mobileId);
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
}
