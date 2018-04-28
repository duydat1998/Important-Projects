/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sample.tbl_Mobile.tbl_MobileDAO;
import sample.tbl_Mobile.tbl_MobileDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class CartObj implements Serializable {

    private Map<String, Integer> items;
    private String userId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public void addItemToCart(String title) {
        //if there is no cart yet-> create new cart
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //in default, the quantity of adding is 1
        int quantity = 1;
        //if the item has been in the cart, just add 1 to quantity
        if (this.items.containsKey(title)) {
            quantity = this.items.get(title) + 1;
        }
        this.items.put(title, quantity);
    }

    public void removeItemFromCart(String title) {
        //if there is no cart -> do nothing
        if (this.items == null) {
            return;
        }
        //if the item is in the cart -> remove
        if (this.items.containsKey(title)) {
            this.items.remove(title);
            //after removing, if the cart is empty -> remove the cart
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public tbl_MobileDTO getMobileById(String Id){
        tbl_MobileDTO result=null;
        tbl_MobileDAO dao=new tbl_MobileDAO();
        try {
            result=dao.getMobileById(Id);
        } catch (NamingException ex) {
            Logger.getLogger(CartObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    

}
