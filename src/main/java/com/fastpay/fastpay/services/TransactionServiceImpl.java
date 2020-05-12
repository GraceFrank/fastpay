/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author grace.frank
 */
@Stateless
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext(unitName = "fastpayUnit")
    EntityManager em;

    @Override
    public void createTransaction(PaymentTransaction transaction) {

        try {
            em.persist(transaction);
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.FINER, "Error saving transaction");

        }
    }

    @Override
    public List<PaymentTransaction> viewUserTransactions(FastpayUser user) {
        Query query = em.createQuery("Select transaction from PaymentTransaction transaction where transaction.userId=:userID");
        query.setParameter("userID", user);

        List<PaymentTransaction> result = query.getResultList();
        if (result != null && result.size() > 0) {
            return result;
        }

        return null;

    }
    
    @Override 
    public List<PaymentTransaction> getAllTransactions() {
        Query query = em.createQuery("Select transactions from PaymentTransaction transactions", PaymentTransaction.class);

        List<PaymentTransaction> result = query.getResultList();
        if (result != null && result.size() > 0) {
            return result;
        }

        return null;

    }
}
