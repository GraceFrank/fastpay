/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.exceptions.PaymentRequestNotFoundException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.Constants;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentRequest;
import com.fastpay.fastpay.services.PaymentRequestService;
import com.fastpay.fastpay.services.RequestManagerService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

/**
 *
 * @author Iben Labaran
 */
@Named
@RequestScoped
public class PaymentRequestBacking extends BaseBacking implements Serializable {

    @EJB
    RequestManagerService requestManager;

    @EJB
    PaymentRequestService paymentRequestService;

    private String infoMessage;
    private String accountId;
    private String description;
    private double amount;
    private List<PaymentRequest> pendingIssuedRequests;
    private List<PaymentRequest> pendingAcquiredRequests;
    private List<PaymentRequest> resolvedPaymentRequests;

    public String sendPaymentRequest() {
        try {
            requestManager.sendPaymentRequest(getUser(), accountId, amount, description);
            infoMessage = "Money Sent to " + accountId + "successfully";
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, "I Finished");

            return "success";

        } catch (UserNotFoundException usf) {
            infoMessage = usf.getMessage();
            getContext().addMessage(null, new FacesMessage("Error processing request, User not  found"));
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, "No user found");

        } catch (Exception ibe) {
            infoMessage = ibe.getMessage();
            getContext().addMessage(null, new FacesMessage("Error sending requests"));
        }
        return null;
    }
    
    public void approveRequest(int requestId){
        try{
      requestManager.approvePaymentRequest( requestId, getUser());
        } catch (Exception ibe) {
            infoMessage = ibe.getMessage();
            getContext().addMessage(null, new FacesMessage("Error approving requests"));
        }
            
    }
    public void rejectRequest(int requestId){
        try{
        requestManager.rejectPaymentRequest(requestId);
        }catch(PaymentRequestNotFoundException prnfe){
            infoMessage = prnfe.getMessage();
            getContext().addMessage(null, new FacesMessage("Error approving requests"));
        }
    }

    public List<PaymentRequest> getPendingIssuedRequests() {
        return paymentRequestService.viewPendingRequests(getUser(), Constants.REQUEST_TYPE_ISSUED);
    }

    public List<PaymentRequest> getPendingAcquiredRequests() {
        return paymentRequestService.viewPendingRequests(getUser(), Constants.REQUEST_TYPE_ACQUIRED);
    }
    
 public List<PaymentRequest> getResolvedPaymentRequests() {
        return paymentRequestService.viewCompletedRequests(getUser());
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
