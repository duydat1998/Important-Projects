/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionSupport;
import sample.tbl_Mobile.tbl_MobileDAO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class UpdateStaffAction extends ActionSupport{
    
    private String mobileId, searchId, searchName;
    private String description;
    private String quantity;
    private String price;
    private boolean notSale;
    
    private final String SUCCESS="success";
    private final String FAIL="fail";
    
    public UpdateStaffAction() {
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        String url=FAIL;
        tbl_MobileDAO dao = new tbl_MobileDAO();
        int tempQuantity=Integer.parseInt(this.quantity);
        float tempPrice=Float.parseFloat(this.price);
        boolean result=dao.updateRecord(mobileId, description, tempPrice, tempQuantity, notSale);
        if(result){
            url=SUCCESS;
        }
        return url;
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
     * @return the searchId
     */
    public String getSearchId() {
        return searchId;
    }

    /**
     * @param searchId the searchId to set
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * @return the searchName
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
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

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
}
