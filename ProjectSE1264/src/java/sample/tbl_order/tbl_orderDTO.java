/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_orderDTO implements Serializable{
    private String orderID, custID, reason;
    private Date orderDate;
    private boolean deliver;
    private float total;

    public tbl_orderDTO() {
    }

    public tbl_orderDTO(String orderID, String custID, String reason, Date orderDate, boolean deliver, float total) {
        this.orderID = orderID;
        this.custID = custID;
        this.reason = reason;
        this.orderDate = orderDate;
        this.deliver = deliver;
        this.total=total;
    }

    
    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the custID
     */
    public String getCustID() {
        return custID;
    }

    /**
     * @param custID the custID to set
     */
    public void setCustID(String custID) {
        this.custID = custID;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the deliver
     */
    public boolean isDeliver() {
        return deliver;
    }

    /**
     * @param deliver the deliver to set
     */
    public void setDeliver(boolean deliver) {
        this.deliver = deliver;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
}
