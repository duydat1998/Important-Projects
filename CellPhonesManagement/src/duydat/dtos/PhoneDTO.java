/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duydat.dtos;

import java.util.Vector;

/**
 *
 * @author Nguyen Duy Dat
 */
public class PhoneDTO {
    //information of a phone
    private String phoneID, phonename, maker,description,link;
    private int price;
    private boolean available;

    //constuctors
    public PhoneDTO() {
    }

    public PhoneDTO(String phoneID, String phonename, String maker, String description, String link, int price, boolean available) {
        this.phoneID = phoneID;
        this.phonename = phonename;
        this.maker = maker;
        this.description = description;
        this.link = link;
        this.price = price;
        this.available = available;
    }

    public PhoneDTO(String phoneID, String phonename, String maker, String description, String link,int price) {
        this.phoneID = phoneID;
        this.phonename = phonename;
        this.maker = maker;
        this.description = description;
        this.link = link;
        this.price = price;
    }
    //getters and setters

    public String getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    //tranform details into a Vector in order to add to the table
    public Vector toVector()
    {
        Vector v=new Vector();
        v.add(phoneID);
        v.add(maker);
        v.add(phonename);
        v.add(description);
        v.add(price);
        return v;
    }
}
