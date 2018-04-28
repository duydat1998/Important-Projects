/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import sample.tbl_Mobile.tbl_MobileDAO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class DeleteRecordAction {
    private String mobileId, searchId, searchName;
    
    private final String SUCCESS="success";
    private final String FAIL="fail";
    
    public DeleteRecordAction() {
    }
    
    public String execute() throws Exception {
        String url=FAIL;
        tbl_MobileDAO dao= new tbl_MobileDAO();
        boolean result=dao.deletePk(mobileId);
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
    
}
