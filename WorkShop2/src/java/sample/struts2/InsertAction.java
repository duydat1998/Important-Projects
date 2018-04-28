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
public class InsertAction extends ActionSupport{
    
    private String insertMobileId, insertDescription, insertMobileName;
    private String searchId, searchName;
    private int insertYear, insertQuatity;
    private float insertPrice;
    private boolean insertNotSale;
    private String successMsq;
    
    private final String SUCCESS="success";
    private final String FAIL="fail";
    
    public InsertAction() {
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        String url=FAIL;
        tbl_MobileDAO dao= new tbl_MobileDAO();
        boolean result=dao.insertNewMobile(insertMobileId, insertDescription, insertMobileName, insertPrice, insertYear, insertQuatity, insertNotSale);
        if(result){
            url=SUCCESS;
            this.successMsq="Insert Successfull!";
        }
        return url;
    }

    /**
     * @return the insertMobileId
     */
    public String getInsertMobileId() {
        return insertMobileId;
    }

    /**
     * @param insertMobileId the insertMobileId to set
     */
    public void setInsertMobileId(String insertMobileId) {
        this.insertMobileId = insertMobileId;
    }

    /**
     * @return the insertDescription
     */
    public String getInsertDescription() {
        return insertDescription;
    }

    /**
     * @param insertDescription the insertDescription to set
     */
    public void setInsertDescription(String insertDescription) {
        this.insertDescription = insertDescription;
    }

    /**
     * @return the insertMobileName
     */
    public String getInsertMobileName() {
        return insertMobileName;
    }

    /**
     * @param insertMobileName the insertMobileName to set
     */
    public void setInsertMobileName(String insertMobileName) {
        this.insertMobileName = insertMobileName;
    }

    /**
     * @return the insertYear
     */
    public int getInsertYear() {
        return insertYear;
    }

    /**
     * @param insertYear the insertYear to set
     */
    public void setInsertYear(int insertYear) {
        this.insertYear = insertYear;
    }
    
    /**
     * @param insertYear the insertYear to set
     */
    public void setInsertYear(String insertYear) {
        try{
            int year=Integer.parseInt(insertYear);
            this.insertYear = year;
        } catch(Exception e){
            this.insertYear=0;
        }
        
    }

    /**
     * @return the insertQuatity
     */
    public int getInsertQuatity() {
        return insertQuatity;
    }

    /**
     * @param insertQuatity the insertQuatity to set
     */
    public void setInsertQuatity(int insertQuatity) {
        this.insertQuatity = insertQuatity;
    }
    
    /**
     * @param insertQuatity the insertQuatity to set
     */
    public void setInsertQuatity(String insertQuatity) {
        try{
            int quantity=Integer.parseInt(insertQuatity);
            this.insertQuatity = quantity;
        } catch(Exception e){
            this.insertQuatity=0;
        }
    }

    /**
     * @return the insertPrice
     */
    public float getInsertPrice() {
        return insertPrice;
    }

    /**
     * @param insertPrice the insertPrice to set
     */
    public void setInsertPrice(float insertPrice) {
        this.insertPrice = insertPrice;
    }

    /**
     * @param insertPrice the insertPrice to set
     */
    public void setInsertPrice(String insertPrice) {
        try {
            float price=Float.parseFloat(insertPrice);
            this.insertPrice = price;
        } catch (Exception e) {
            this.insertPrice=0;
        }
        
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
     * @return the insertNotSale
     */
    public boolean isInsertNotSale() {
        return insertNotSale;
    }

    /**
     * @param insertNotSale the insertNotSale to set
     */
    public void setInsertNotSale(boolean insertNotSale) {
        this.insertNotSale = insertNotSale;
    }

    /**
     * @return the successMsq
     */
    public String getSuccessMsq() {
        return successMsq;
    }

    /**
     * @param successMsq the successMsq to set
     */
    public void setSuccessMsq(String successMsq) {
        this.successMsq = successMsq;
    }
    
}
