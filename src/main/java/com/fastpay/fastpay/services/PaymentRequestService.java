/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.PaymentRequestNotFoundException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentRequest;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Iben Labaran
 */
@Local
public interface PaymentRequestService {

    public void createPaymentRequest(String userId, String participant, double amount, String type, 
                                    String description, String requestDate ) throws UserNotFoundException;

    public PaymentRequest updatePaymentRequestStatus(long requestId, String status) throws PaymentRequestNotFoundException;

    public List<PaymentRequest> viewPendingRequests(FastpayUser user, String type);

    public List<PaymentRequest> viewCompletedRequests(FastpayUser user);
    
    public PaymentRequest getRequestByUserId(FastpayUser user, String requestDate) throws PaymentRequestNotFoundException;
}
