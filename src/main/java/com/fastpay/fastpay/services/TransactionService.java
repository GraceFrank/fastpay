/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentTransaction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author iben.labaran
 */
@Local
public interface TransactionService {
    
    public void createTransaction(PaymentTransaction transaction);
    public List<PaymentTransaction> viewUserTransactions(FastpayUser user);
    public List<PaymentTransaction> getAllTransactions();
}
