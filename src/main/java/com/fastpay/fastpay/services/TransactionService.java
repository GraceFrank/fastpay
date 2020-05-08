/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.PaymentTransaction;
import javax.ejb.Local;

/**
 *
 * @author grace.frank
 */
@Local
public interface TransactionService {
    
    public void createTransaction(PaymentTransaction transaction);
}
