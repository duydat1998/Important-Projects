/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import sample.cart.CartObj;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class AddToCartAction {
    private String minPrice, maxPrice;
    private String mobileId;
    private String successMsg;
    private final String SUCCESS="success";

    public AddToCartAction() {
    }
    
    public String execute() throws Exception {
        Map session=ActionContext.getContext().getSession();
        CartObj cart= (CartObj) session.get("CART");
        if(cart == null){
            cart= new CartObj();
        }
        tbl_MobileDAO dao= new tbl_MobileDAO();
        tbl_MobileDTO dto= dao.getMobileById(mobileId);
        cart.addItemToCart(dto);
        session.put("CART", cart);
        successMsg="The mobile is added to cart";
        return SUCCESS;
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
     * @return the successMsg
     */
    public String getSuccessMsg() {
        return successMsg;
    }

    /**
     * @param successMsg the successMsg to set
     */
    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
    
}
