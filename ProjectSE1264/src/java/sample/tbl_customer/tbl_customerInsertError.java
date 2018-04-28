/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_customer;

import java.io.Serializable;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_customerInsertError implements Serializable{
    private String custIDLengthError;
    private String custIDDuplicated;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String custNameLengthError;
    private String lastNameLengthError;
    private String middleNameLengthError;
    private String addressLengthError;
    private String phoneLengthError;

    public tbl_customerInsertError() {
    }

    /**
     * @return the custIDLengthError
     */
    public String getCustIDLengthError() {
        return custIDLengthError;
    }

    /**
     * @param custIDLengthError the custIDLengthError to set
     */
    public void setCustIDLengthError(String custIDLengthError) {
        this.custIDLengthError = custIDLengthError;
    }

    /**
     * @return the custIDDuplicated
     */
    public String getCustIDDuplicated() {
        return custIDDuplicated;
    }

    /**
     * @param custIDDuplicated the custIDDuplicated to set
     */
    public void setCustIDDuplicated(String custIDDuplicated) {
        this.custIDDuplicated = custIDDuplicated;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the custNameLengthError
     */
    public String getCustNameLengthError() {
        return custNameLengthError;
    }

    /**
     * @param custNameLengthError the custNameLengthError to set
     */
    public void setCustNameLengthError(String custNameLengthError) {
        this.custNameLengthError = custNameLengthError;
    }

    /**
     * @return the lastNameLengthError
     */
    public String getLastNameLengthError() {
        return lastNameLengthError;
    }

    /**
     * @param lastNameLengthError the lastNameLengthError to set
     */
    public void setLastNameLengthError(String lastNameLengthError) {
        this.lastNameLengthError = lastNameLengthError;
    }

    /**
     * @return the middleNameLengthError
     */
    public String getMiddleNameLengthError() {
        return middleNameLengthError;
    }

    /**
     * @param middleNameLengthError the middleNameLengthError to set
     */
    public void setMiddleNameLengthError(String middleNameLengthError) {
        this.middleNameLengthError = middleNameLengthError;
    }

    /**
     * @return the addressLengthError
     */
    public String getAddressLengthError() {
        return addressLengthError;
    }

    /**
     * @param addressLengthError the addressLengthError to set
     */
    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    /**
     * @return the phoneLengthError
     */
    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    /**
     * @param phoneLengthError the phoneLengthError to set
     */
    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }
    
}
