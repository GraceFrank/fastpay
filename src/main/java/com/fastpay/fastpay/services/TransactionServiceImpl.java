/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.PaymentTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
