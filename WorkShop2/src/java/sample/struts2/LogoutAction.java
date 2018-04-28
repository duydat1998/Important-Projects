/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Nguyen Duy Dat
 */
public class LogoutAction {
    private final String SUCCESS="success";
    
    public LogoutAction() {
    }
    
    public String execute() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        HttpSession session= request.getSession();
        session.invalidate();
        return SUCCESS;
    }
    
}
