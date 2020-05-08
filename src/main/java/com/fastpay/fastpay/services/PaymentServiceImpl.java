/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.InsuficientBalanceException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentTransaction;
import com.fastpay.fastpay.utils.CurrencyConverter;
import com.fastpay.fastpay.utils.TimeStamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author grace.frank
 */
@Stateful
public class PaymentServiceImpl implements PaymentService {

    @PersistenceContext(unitName = "fastpayUnit", type = PersistenceContextType.EXTENDED)
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
    public void sendMoney(String reciverId, FastpayUser sender, double amount) throws UserNotFoundException, InsuficientBalanceException {
        if (sender.getAccountBalance() < amount) {
            throw new InsuficientBalanceException();
        }

        try {
            FastpayUser receiver = userManager.getFastpayUser(reciverId);
            double receivableAmount = currencyConverter.convert(sender.getCurrency(), receiver.getCurrency(), amount);
            receiver.setAccountBalance(receivableAmount);
            //update receivers account
            userManager.updateUser(receiver);
            //create transaction alert
            PaymentTransaction creditAlert = new PaymentTransaction();
            creditAlert.setTransactionAmount(receivableAmount);
            creditAlert.setUserId(receiver);
            creditAlert.setParticipant(sender.getId() + sender.getFirstName() + sender.getLastName());
            creditAlert.setTransactionDate(timestamp.getCurrentTime());
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
            transactionService.createTransaction(debitAlert);

        } catch (UserNotFoundException une) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.FINER, "No user found");
        } catch (Exception e) {
            Logger.getLogger(PaymentService.class.getName()).log(Level.FINER, "Error making payment" + e.getMessage());
        }

    }

}
