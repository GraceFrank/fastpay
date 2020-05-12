/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.InsuficientBalanceException;
import com.fastpay.fastpay.exceptions.InvalidRequestException;
import com.fastpay.fastpay.exceptions.PaymentRequestNotFoundException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.FastpayUser;
import javax.ejb.Local;

/**
 *
 * @author Iben Labaran
 */
@Local
public interface RequestManagerService {

    public void sendPaymentRequest(FastpayUser Issuer, String acquirer, double amount, String description) throws UserNotFoundException;

    public void rejectPaymentRequest(long requestId) throws PaymentRequestNotFoundException;

    public void approvePaymentRequest(long requestId, FastpayUser user) throws PaymentRequestNotFoundException, InsuficientBalanceException;
}
