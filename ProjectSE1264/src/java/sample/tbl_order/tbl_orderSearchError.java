/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

import java.io.Serializable;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_orderSearchError implements Serializable{
    private String invalidFromDate;
    private String invalidToDate;
    private String invalidTimeInterval;
    private String requieredFromDate;
    private String requieredToDate;
    
    

    public tbl_orderSearchError() {
    }

    /**
     * @return the invalidFromDate
     */
    public String getInvalidFromDate() {
        return invalidFromDate;
    }

    /**
     * @param invalidFromDate the invalidFromDate to set
     */
    public void setInvalidFromDate(String invalidFromDate) {
        this.invalidFromDate = invalidFromDate;
    }

    /**
     * @return the invalidToDate
     */
    public String getInvalidToDate() {
        return invalidToDate;
    }

    /**
     * @param invalidToDate the invalidToDate to set
     */
    public void setInvalidToDate(String invalidToDate) {
        this.invalidToDate = invalidToDate;
    }

    /**
     * @return the invalidTimeInterval
     */
    public String getInvalidTimeInterval() {
        return invalidTimeInterval;
    }

    /**
     * @param invalidTimeInterval the invalidTimeInterval to set
     */
    public void setInvalidTimeInterval(String invalidTimeInterval) {
        this.invalidTimeInterval = invalidTimeInterval;
    }

    /**
     * @return the requieredFromDate
     */
    public String getRequieredFromDate() {
        return requieredFromDate;
    }

    /**
     * @param requieredFromDate the requieredFromDate to set
     */
    public void setRequieredFromDate(String requieredFromDate) {
        this.requieredFromDate = requieredFromDate;
    }

    /**
     * @return the requieredToDate
     */
    public String getRequieredToDate() {
        return requieredToDate;
    }

    /**
     * @param requieredToDate the requieredToDate to set
     */
    public void setRequieredToDate(String requieredToDate) {
        this.requieredToDate = requieredToDate;
    }
    
    
}
