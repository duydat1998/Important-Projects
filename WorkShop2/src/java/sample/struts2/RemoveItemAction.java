/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import sample.cart.CartObj;

/**
 *
 * @author Nguyen Duy Dat
 */
public class RemoveItemAction {

    private String[] selectedItem;
    private final String SUCCESS = "success";

    public RemoveItemAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("CART");
        if (cart != null) {
            for (String id : getSelectedItem()) {
                cart.removeItemFromCart(id);
            }
            if (cart.getItems() == null) {
                session.remove("CART");
            } else{
                session.put("CART", cart);
            }
        }
        return SUCCESS;
    }

    /**
     * @return the selectedItem
     */
    public String[] getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(String[] selectedItem) {
        this.selectedItem = selectedItem;
    }

}
