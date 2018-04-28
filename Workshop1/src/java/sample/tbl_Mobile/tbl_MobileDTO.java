/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_Mobile;

import java.io.Serializable;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_MobileDTO implements Serializable{
    
    private String mobileId, description, mobileName;
    private Float price;
    private Integer yearOfProduction, quantity;
    private boolean notSale;

    public tbl_MobileDTO() {
    }

    public tbl_MobileDTO(String mobileId, String description, String mobileName, Float price, Integer yearOfProduction, Integer quantity, boolean notSale) {
        this.mobileId = mobileId;
        this.description = description;
        this.mobileName = mobileName;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.quantity = quantity;
        this.notSale = notSale;
    }

    /**
     * @return the mobileId
     */
    public String getMobileId() {
        return mobileId;
    }

    /**
     * @param mobileId the mobileId to set
     */
    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the mobileName
     */
    public String getMobileName() {
        return mobileName;
    }

    /**
     * @param mobileName the mobileName to set
     */
    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the yearOfProduction
     */
    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * @param yearOfProduction the yearOfProduction to set
     */
    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the notSale
     */
    public boolean isNotSale() {
        return notSale;
    }

    /**
     * @param notSale the notSale to set
     */
    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }
    
    
}
