/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.binary.app.restoppress.restoppress.domain;

/**
 *
 * @author minion
 */
public class Message{
    private String amount;
    private String currency;
    private String merchantID;
    private String referenceNo;
    private String orderInfo;
    private String receiptNo;
    private String authId;
    private String responseCode;
    private String transactionNo;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransacionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
    public String toString(){
        return "Amount=>"+amount+", Currency=>"+currency+", MerchantId=>"+merchantID+", RefNo=>"+referenceNo+","
                + " OrderInfo=>"+orderInfo+", ReceiptNo=>"+receiptNo+", AuthId=>"+authId+", AuthId=>"+authId+","
                + " ResponseCode=>"+responseCode+", TransactionNo=>"+transactionNo;
    }
    
}
