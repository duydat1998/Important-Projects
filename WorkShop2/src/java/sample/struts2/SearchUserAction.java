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
public class SearchUserAction{

    private String minPrice, maxPrice;
    private String successMsg;
    private List<tbl_MobileDTO> listMobile;
    private final String SUCCESS = "success";

    public SearchUserAction() {
    }

    public String execute() throws Exception {
        System.out.println("SearchAction");
        String url = SUCCESS;
        float min=Float.parseFloat(getMinPrice());
        float max=Float.parseFloat(getMaxPrice());
        tbl_MobileDAO dao = new tbl_MobileDAO();
        listMobile = dao.searchMobileByPriceRange(min, max, false);
        return url;
    }

    

    /**
     * @return the listMobile
     */
    public List<tbl_MobileDTO> getListMobile() {
        return listMobile;
    }

    /**
     * @param listMobile the listMobile to set
     */
    public void setListMobile(List<tbl_MobileDTO> listMobile) {
        this.listMobile = listMobile;
    }

    /**
     * @return the minPrice
     */
    public String getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice the minPrice to set
     */
    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return the maxPrice
     */
    public String getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice the maxPrice to set
     */
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return the successMsq
     */
    public String getSuccessMsg() {
        return successMsg;
    }

    /**
     * @param successMsq the successMsq to set
     */
    public void setSuccessMsg(String successMsq) {
        this.successMsg = successMsq;
    }

}
