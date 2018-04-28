/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import sample.cart.CartObj;
import sample.tbl_Mobile.tbl_MobileDTO;
import sample.tbl_Order.tbl_OrderDAO;
import sample.tbl_OrderDetail.tbl_OrderDetailDAO;
import sample.tbl_User.tbl_UserDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class CheckOutAction {
    private final String SUCCESS="success";
    private final String FAIL="fail";
    
    public CheckOutAction() {
    }
    
    public String execute() throws Exception {
        String url=FAIL;
        boolean result=false;
        Map session= ActionContext.getContext().getSession();
        CartObj cart=(CartObj) session.get("CART");
        tbl_UserDTO dto= (tbl_UserDTO) session.get("USER");
        if (cart != null) {
                    tbl_OrderDAO orderDao = new tbl_OrderDAO();
                    int total = 0;
                    float totalAmount=0;
                    Map<tbl_MobileDTO, Integer> items = cart.getItems();
                    for (Map.Entry<tbl_MobileDTO, Integer> item : items.entrySet()) {
                        Integer quantity = item.getValue();
                        total += quantity;
                        float price= item.getKey().getPrice();
                        totalAmount+=(price*quantity);
                    }//end for
                    
                    int generatedKey = orderDao.insertOrder(total, dto.getUserId(), totalAmount);
                    if (generatedKey > 0) {
                        for (Map.Entry<tbl_MobileDTO, Integer> item : items.entrySet()) {
                            String mobileId = item.getKey().getMobileId();
                            Integer quantity = item.getValue();
                            tbl_OrderDetailDAO detailDao = new tbl_OrderDetailDAO();
                            result = detailDao.insertOrderDetail(mobileId, quantity, generatedKey);
                        }//end for
                        if (result) {
                            url = SUCCESS;
                            session.remove("CART");
                        }
                    }// end if generatedKey
                }//end if cart
        return url;
    }
    
}
