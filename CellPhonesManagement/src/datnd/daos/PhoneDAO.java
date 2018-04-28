/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datnd.daos;

import datnd.db.MyConnection;
import duydat.dtos.PhoneDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Duy Dat
 */
public class PhoneDAO implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    //constructors
    public PhoneDAO() {
    }
    
    //method to close all connections, avoid errors and exceptions
    private void closeConnection()
    {
        try {
                if(rs!=null)
                    rs.close();
                if(preStm!=null)
                    preStm.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
    }
    
    //method return to a list of Phones which are available (not be disabled)
    public List<PhoneDTO> loadAllPhones(int available) throws Exception{
        List<PhoneDTO> list=null;
        try{
            list= new ArrayList<>();
            String sql="Select PhoneID,Phonename,Maker,Description,Price,Image,Available from Phones where Available=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setInt(1, available);
            rs=preStm.executeQuery();
            while(rs.next())
            {
                String phoneID=rs.getString("PhoneID");
                String phonename=rs.getString("Phonename");
                String maker=rs.getString("Maker");
                String description=rs.getString("Description");
                int price=Integer.parseInt(rs.getString("Price"));
                String image=rs.getString("Image");
                boolean isAvailable=rs.getBoolean("Available");
                list.add(new PhoneDTO(phoneID, phonename, maker, description, image, price,isAvailable));
            }
        }
        finally{
            closeConnection();
        }
        return list;
    }
    
    //method to get the image link of a phone based on ID
    public String getLinkImage(String phoneID) throws Exception{
        String link=null;
        try{
            String sql="Select Image from Phones where PhoneID=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, phoneID);
            rs=preStm.executeQuery();
            if(rs.next())
                link=rs.getString("Image");
        } finally{
            closeConnection();
        }
        return link;
    }
    
    //method to insert a phone into table Phones
    // default available is True
    public boolean insertPhone(PhoneDTO dto) throws Exception{
        boolean result=false;
        try{
            String sql="Insert into Phones (PhoneID,Phonename,Maker,Description,Price,Image,Available) values (?,?,?,?,?,?,1)";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, dto.getPhoneID());
            preStm.setString(2, dto.getPhonename());
            preStm.setString(3, dto.getMaker());
            preStm.setString(4, dto.getDescription());
            preStm.setString(5, dto.getPrice()+"");
            preStm.setString(6, dto.getLink());
            result=preStm.executeUpdate()>0;
        } finally{
            closeConnection();
        }
        return result;
    }
    
    //method to check if the Phone is in database, based on PhoneID
    public boolean isPhoneIDExisted(String phoneID) throws Exception{
        boolean result=false;
        try{
            String sql="Select Phonename from Phones where PhoneID=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, phoneID);
            rs=preStm.executeQuery();
            if(rs.next())
                result=true;
        } finally{
            closeConnection();
        }
        return result;
    }
    
    //method to update phone's details , return true if successfull or false if failed
    //each detail will be check if it is not null before updated in order to avoid errors
    public boolean updatePhoneInformation(String phoneID,String phonename, String maker, String description , String price, String image)throws Exception{
        boolean result=false;
        try{
            String sql;
            if(phonename.length()!=0)
            {
                sql="Update Phones set Phonename=? where PhoneID=?";
                conn=MyConnection.getMyConnection();
                preStm=conn.prepareStatement(sql);
                preStm.setString(1, phonename);
                preStm.setString(2, phoneID);
                result=preStm.executeUpdate()>0;
            }
            if(maker.length()!=0)
            {
                sql="Update Phones set Maker=? where PhoneID=?";
                conn=MyConnection.getMyConnection();
                preStm=conn.prepareStatement(sql);
                preStm.setString(1, maker);
                preStm.setString(2, phoneID);
                result=preStm.executeUpdate()>0;
            }
            if(description.length()!=0)
            {
                sql="Update Phones set Description=? where PhoneID=?";
                conn=MyConnection.getMyConnection();
                preStm=conn.prepareStatement(sql);
                preStm.setString(1, description);
                preStm.setString(2, phoneID);
                result=preStm.executeUpdate()>0;
            }
            if(image.length()!=0)
            {
                sql="Update Phones set Image=? where PhoneID=?";
                conn=MyConnection.getMyConnection();
                preStm=conn.prepareStatement(sql);
                preStm.setString(1, image);
                preStm.setString(2, phoneID);
                result=preStm.executeUpdate()>0;
            }
            if(price.length()!=0)
            {
                sql="Update Phones set price=? where PhoneID=?";
                conn=MyConnection.getMyConnection();
                preStm=conn.prepareStatement(sql);
                int priceInt=Integer.parseInt(price);
                preStm.setInt(1, priceInt);
                preStm.setString(2, phoneID);
                result=preStm.executeUpdate()>0;
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    
    //method to disable a phone, which means that: set Available to that phone: false
    //in order to keep it details for further used
    public boolean disablePhone(String phoneID) throws Exception{
        boolean result=false;
        try {
            String sql="Update Phones set Available=0 where PhoneID=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, phoneID);
            result=preStm.executeUpdate()>0;
        } finally {
            closeConnection();
        }
            
        return result; 
    }
    
    //method to enable a phone, which means that: set Available to that phone: true
    //in order to keep it details for further used
    public boolean enablePhone(String phoneID) throws Exception{
        boolean result=false;
        try {
            String sql="Update Phones set Available=1 where PhoneID=?";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, phoneID);
            result=preStm.executeUpdate()>0;
        } finally {
            closeConnection();
        }
            
        return result; 
    }
    
    //method return a list of phones base on Phonename
    public List<PhoneDTO> searchByPhonename(String search ) throws Exception{
        List<PhoneDTO> list=null;
        try {
            list=new ArrayList<>();
            String sql="Select PhoneID,Maker,Description,Price,Image,Phonename from Phones where Phonename LIKE ? and Available=1";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, "%"+search+"%");
            rs=preStm.executeQuery();
            while (rs.next()){
                String phonename=rs.getString("Phonename");
                String phoneID=rs.getString("PhoneID");
                String maker=rs.getString("Maker");
                String description=rs.getString("Description");
                int price=Integer.parseInt(rs.getString("Price"));
                String image=rs.getString("Image");
                list.add(new PhoneDTO(phoneID, phonename, maker, description, image, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    //method return a list of phones base on Maker such as: Samsung, Apple, ASUS,...
    public List<PhoneDTO> searchByMaker(String search) throws Exception{
        List<PhoneDTO> list=null;
        try {
            list=new ArrayList<>();
            String sql="Select PhoneID,Phonename,Description,Price,Image,Maker from Phones where Maker LIKE ? and Available=1";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, "%"+search+"%");
            rs=preStm.executeQuery();
            while (rs.next()){
                String maker=rs.getString("Maker");
                String phoneID=rs.getString("PhoneID");
                String phonename=rs.getString("Phonename");
                String description=rs.getString("Description");
                int price=Integer.parseInt(rs.getString("Price"));
                String image=rs.getString("Image");
                list.add(new PhoneDTO(phoneID, phonename, maker, description, image, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    //method return a list of phones base on Price
    public List<PhoneDTO> searchByPrice(String from, String to) throws Exception{
        List<PhoneDTO> list=null;
        try {
            int fromPrice=Integer.parseInt(from);
            int toPrice=Integer.parseInt(to);
            list=new ArrayList<>();
            String sql="Select PhoneID,Phonename,Maker,Description,Price,Image from Phones where (Price BETWEEN ? AND ?) and Available=1";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setInt(1, fromPrice);
            preStm.setInt(2, toPrice);
            rs=preStm.executeQuery();
            while (rs.next()){
                String phoneID=rs.getString("PhoneID");
                String maker=rs.getString("Maker");
                String phonename=rs.getString("Phonename");
                String description=rs.getString("Description");
                int price=Integer.parseInt(rs.getString("Price"));
                String image=rs.getString("Image");
                list.add(new PhoneDTO(phoneID, phonename, maker, description, image, price));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    //method return a phone of phones base on PhoneID
    public PhoneDTO searchByPhoneID(String ID) throws Exception{
        PhoneDTO dto=null;
        try {
            String sql="Select Phonename,Maker,Description,Price,Image from Phones where PhoneID=? and Available=1";
            conn=MyConnection.getMyConnection();
            preStm=conn.prepareStatement(sql);
            preStm.setString(1, ID);
            rs=preStm.executeQuery();
            if(rs.next()){
                String phoneName=rs.getString("Phonename");
                String maker=rs.getString("Maker");
                String description=rs.getString("Description");
                int price=Integer.parseInt(rs.getString("Price"));
                String image=rs.getString("Image");
                dto=new PhoneDTO(ID, phoneName, maker, description, image, price);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
}
