/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datnd.daos;

import datnd.db.MyConnection;
import duydat.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Duy Dat
 */
public class UserDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    //constuctors
    public UserDAO() {
    }
    
    
    private void closeConnection() throws Exception
    {
            if(rs!=null)
                rs.close();
            if(preStm!=null)
                preStm.close();
            if(conn!=null)
                conn.close();
    }
    
    public String checkLogin(String username, String password) throws Exception
    {
        String role="failed";
        try {
            String sql="Select Role from Users where Username=? and Password=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs=preStm.executeQuery();
            if (rs.next()){
                role=rs.getString("Role");
            }
        }
        finally{
            closeConnection();
        }
        return role;
    }
    
    public UserDTO searchByUsername(String username) throws Exception{
        UserDTO dto=null;
        try{
            String sql="Select Password, Fullname,Role from Users where Username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs=preStm.executeQuery();
            if(rs.next())
            {
                String password=rs.getString("Password");
                String fullname=rs.getString("Fullname");
                String role=rs.getString("Role");
                dto=new UserDTO(username, fullname, role, password);
            }
        } finally{
            closeConnection();
        }
        return dto;
    }
    
    public boolean updateUserProfile(UserDTO dto) throws Exception{
        boolean result=false;
        try{
            String sql="Update Users set Password=?,Fullname=?,Role=? where Username=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());
            preStm.setString(2, dto.getFullname());
            preStm.setString(3, dto.getRole());
            preStm.setString(4, dto.getUsername());
            result=preStm.executeUpdate()>0;
        } finally{
            closeConnection();
        }
        return result;
    }
}
