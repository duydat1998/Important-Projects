/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class CartObj implements Serializable {

    private Map<tbl_MobileDTO, Integer> items;
    private String userId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    public Map<tbl_MobileDTO, Integer> getItems() {
        return items;
    }

    public void setItems(Map<tbl_MobileDTO, Integer> items) {
        this.items = items;
    }

    public void addItemToCart(tbl_MobileDTO dto) {
        //if there is no cart yet-> create new cart
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //in default, the quantity of adding is 1
        int quantity = 1;
        //if the item has been in the cart, just add 1 to quantity
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }
        this.items.put(dto, quantity);
    }

    public void removeItemFromCart(String mobileId) {
        //if there is no cart -> do nothing
        if (this.items == null) {
            return;
        }
        tbl_MobileDTO dto= new tbl_MobileDTO(mobileId, "", "", Float.MAX_VALUE, 0, 0, true);
        //if the item is in the cart -> remove
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            //after removing, if the cart is empty -> remove the cart
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public void removeItemFromCart(tbl_MobileDTO dto) {
        //if there is no cart -> do nothing
        if (this.items == null) {
            return;
        }
        //if the item is in the cart -> remove
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            //after removing, if the cart is empty -> remove the cart
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    

}
