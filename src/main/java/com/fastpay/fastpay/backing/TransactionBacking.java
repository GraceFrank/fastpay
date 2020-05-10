/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.services.TransactionService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author grace.frank
 */

@Named
@RequestScoped
public class TransactionBacking extends BaseBacking implements Serializable{
    
    private static final long serialVersionUID = 1094801825228986363L;

    @EJB
    private TransactionService transactionService;
    
    private List userTransactions;
    
    private FastpayUser user = getUser();
    
    @PostConstruct
    public void initialize(){
       userTransactions = transactionService.viewUserTransactions(user);
    }

    public List getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(List userTransactions) {
        this.userTransactions = userTransactions;
    }
    
    
    
    
}
