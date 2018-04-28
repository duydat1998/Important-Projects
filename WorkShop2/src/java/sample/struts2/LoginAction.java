/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import sample.tbl_User.tbl_UserDAO;
import sample.tbl_User.tbl_UserDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class LoginAction {
    private String userId;
    private int password;
    
    private final String USER="user";
    private final String MANAGER="manager";
    private final String STAFF="staff";
    private final String FAIL="fail";
    
    public LoginAction() {
    }
    
    public String execute() throws Exception {
        String url=FAIL;
        tbl_UserDAO dao= new tbl_UserDAO();
        tbl_UserDTO dto=dao.checkLogin(userId, password);
        if(dto != null){
            int role=dto.getRole();
            switch (role) {
                case 0:
                    url=USER;
                    break;
                case 1:
                    url=MANAGER;
                    break;
                default:
                    url=STAFF;
                    break;
            }
            Map session=ActionContext.getContext().getSession();
            session.put("USER", dto);
        }
        return url;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the password
     */
    public int getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(int password) {
        this.password = password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        if(!password.matches("^\\d+$")){
            this.password=0;
        } else{
            Integer temp=Integer.parseInt(password);
            this.password=temp;
        }
    }
}
