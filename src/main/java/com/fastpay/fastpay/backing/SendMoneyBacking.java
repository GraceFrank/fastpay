/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.exceptions.InsuficientBalanceException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.services.PaymentService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Iben Labaran
 */
@Named
@RequestScoped
public class SendMoneyBacking extends BaseBacking implements Serializable {

    @EJB
    PaymentService paymentService;

    private String infoMessage;
    private String accountId;
    private String description;
    private double amount;

    public String sendMoney() {
        try {
            paymentService.sendMoney(accountId, getUser(), amount, description);
            infoMessage = "Money Sent to " + accountId + "successfully";
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, "I Finished");

            return "success";
        } catch (InsuficientBalanceException ibe) {
            infoMessage = ibe.getMessage();
            getContext().addMessage(null, new FacesMessage("Error processing request, Insufficient Fund"));

        } catch (UserNotFoundException usf) {
            infoMessage = usf.getMessage();
            getContext().addMessage(null, new FacesMessage("Error processing request, User not  found"));
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, "No user found");

        }
        return null;
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
