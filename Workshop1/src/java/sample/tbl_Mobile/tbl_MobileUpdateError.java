/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_Mobile;

/**
 *
 * @author Nguyen Duy Dat
 */
public class tbl_MobileUpdateError {

    public tbl_MobileUpdateError() {
    }
    
    private String descriptionBlankError;
    private String priceFormatError;
    private String quantityFormatError;
    private String mobileId;

    /**
     * @return the descriptionBlankError
     */
    public String getDescriptionBlankError() {
        return descriptionBlankError;
    }

    /**
     * @param descriptionBlankError the descriptionBlankError to set
     */
    public void setDescriptionBlankError(String descriptionBlankError) {
        this.descriptionBlankError = descriptionBlankError;
    }

    /**
     * @return the priceFormatError
     */
    public String getPriceFormatError() {
        return priceFormatError;
    }

    /**
     * @param priceFormatError the priceFormatError to set
     */
    public void setPriceFormatError(String priceFormatError) {
        this.priceFormatError = priceFormatError;
    }

    /**
     * @return the quantityFormatError
     */
    public String getQuantityFormatError() {
        return quantityFormatError;
    }

    /**
     * @param quantityFormatError the quantityFormatError to set
     */
    public void setQuantityFormatError(String quantityFormatError) {
        this.quantityFormatError = quantityFormatError;
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
    
}
