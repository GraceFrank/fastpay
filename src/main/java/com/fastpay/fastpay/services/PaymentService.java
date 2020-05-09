/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.InsuficientBalanceException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.FastpayUser;
import javax.ejb.Local;

/**
 *
 * @author grace.frank
 */
@Local
public interface PaymentService {
        public void sendMoney(String receiverId, FastpayUser sender, double amount, String description) throws InsuficientBalanceException, UserNotFoundException;

}
