/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentTransaction;
import com.fastpay.fastpay.services.FastpayUserManager;
import com.fastpay.fastpay.services.TransactionService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

/**
 *
 * @author Iben Labaran
 */
@Named
@RequestScoped
public class AdminBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1094801825828386363L;

    @EJB
    private TransactionService transactionService;
    
    @EJB
    private FastpayUserManager userManager;

    private List<PaymentTransaction> transactions;
    private List<FastpayUser> users;

    public List<PaymentTransaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    public List<FastpayUser> getUsers() {
        return userManager.getAllUsers();
    }

    public void createAdminUser() {

    }
}
