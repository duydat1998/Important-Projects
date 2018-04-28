/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.util.List;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class SearchStaffAction {
    private String searchId, searchName;
    private List<tbl_MobileDTO> listId;
    private List<tbl_MobileDTO> listName;
    
    private final String SUCCESS="success";
    
    public SearchStaffAction() {
    }
    
    public String execute() throws Exception {
        String url=SUCCESS;
        
        tbl_MobileDAO dao= new tbl_MobileDAO();
        if(!searchId.isEmpty()){
            listId=dao.searchMobileById(searchId);
        }
        
        if(!searchName.isEmpty()){
            listName=dao.searchMobileByName(searchName);
        }
        return url;
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
     * @return the listId
     */
    public List<tbl_MobileDTO> getListId() {
        return listId;
    }

    /**
     * @param listId the listId to set
     */
    public void setListId(List<tbl_MobileDTO> listId) {
        this.listId = listId;
    }

    /**
     * @return the listName
     */
    public List<tbl_MobileDTO> getListName() {
        return listName;
    }

    /**
     * @param listName the listName to set
     */
    public void setListName(List<tbl_MobileDTO> listName) {
        this.listName = listName;
    }
    
}
