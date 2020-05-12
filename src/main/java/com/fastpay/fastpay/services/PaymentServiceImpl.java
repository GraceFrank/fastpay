/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.InsuficientBalanceException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.Constants;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentTransaction;
import com.fastpay.fastpay.utils.CurrencyConverter;
import com.fastpay.fastpay.utils.TimeStamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author iben.labaran
 */
@Stateless
public class PaymentServiceImpl implements PaymentService {

    @PersistenceContext(unitName = "fastpayUnit")
    EntityManager em;

    @EJB
    private FastpayUserManager userManager;

    @EJB
    private TransactionService transactionService;

    @EJB
    private CurrencyConverter currencyConverter;

    @EJB
    private TimeStamp timestamp;

    @Override
    public void sendMoney(String reciverId, FastpayUser sender, double amount, String description) throws UserNotFoundException, InsuficientBalanceException {
        if (sender.getAccountBalance() < amount) {
            throw new InsuficientBalanceException();
        }

        try {
            FastpayUser receiver = userManager.getFastpayUser(reciverId);
            double receivableAmount = currencyConverter.convert(sender.getCurrency(), receiver.getCurrency(), amount);
            receiver.setAccountBalance(receivableAmount + receiver.getAccountBalance());
            //update receivers account
            userManager.updateUser(receiver);
            //create transaction alert
            PaymentTransaction creditAlert = new PaymentTransaction();
            creditAlert.setTransactionAmount(receivableAmount);
            creditAlert.setUserId(receiver);
            creditAlert.setParticipant(sender.getId() +" " + sender.getFirstName() + " " +sender.getLastName());
            creditAlert.setTransactionDate(timestamp.getCurrentTime());
            creditAlert.setDescription(description);
            creditAlert.setTransactionType(Constants.CREDIT);
            transactionService.createTransaction(creditAlert);

            sender.setAccountBalance(sender.getAccountBalance() - amount);
            //update sender account
            userManager.updateUser(sender);
            //create transaction
            PaymentTransaction debitAlert = new PaymentTransaction();
            debitAlert.setTransactionAmount(amount);
            debitAlert.setUserId(sender);
            debitAlert.setParticipant(receiver.getId() + receiver.getFirstName() + receiver.getLastName());
            debitAlert.setTransactionDate(timestamp.getCurrentTime());
            debitAlert.setDescription(description);
            debitAlert.setTransactionType(Constants.DEBIT);
            transactionService.createTransaction(debitAlert);

        } catch (UserNotFoundException une) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, "Error making payment" + e.getMessage());
        }

    }

}
