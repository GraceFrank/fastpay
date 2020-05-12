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
import com.fastpay.fastpay.models.Constants;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentRequest;
import com.fastpay.fastpay.utils.CurrencyConverter;
import com.fastpay.fastpay.utils.TimeStamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Iben Labaran
 */
@Stateful
public class RequestManagerServiceImpl implements RequestManagerService {

    @PersistenceContext(unitName = "fastpayUnit", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    @EJB
    private FastpayUserManager userManager;

    @EJB
    private PaymentService paymentService;

    @EJB
    private CurrencyConverter currencyConverter;

    @EJB
    private TimeStamp timestamp;

    @EJB
    private PaymentRequestService paymentRequestService;

    @Override
    public void sendPaymentRequest(FastpayUser issuer, String acquirerId, double amount, String description) throws UserNotFoundException {
        String requestDate = timestamp.getCurrentTime();
        try {
            paymentRequestService.createPaymentRequest(issuer.getId(), acquirerId, amount,
                    Constants.REQUEST_TYPE_ISSUED, description, requestDate);
            
                        FastpayUser acquirer = userManager.getFastpayUser(acquirerId);
                        double covertedAmount = currencyConverter.convert(issuer.getCurrency(), acquirer.getCurrency(), amount);


            paymentRequestService.createPaymentRequest(acquirerId, issuer.getId() + " " + issuer.getFirstName() + " " +issuer.getLastName(),
                    covertedAmount, Constants.REQUEST_TYPE_ACQUIRED, description, requestDate);

        } catch (UserNotFoundException uae) {
            throw new UserNotFoundException();
        } //catch (Exception e) {
//            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, "Error saving transaction");
//
//        }
    }

    @Override
    public void rejectPaymentRequest(long requestId) throws PaymentRequestNotFoundException {
        try {

            PaymentRequest updatedPaymentRequest = paymentRequestService.updatePaymentRequestStatus(requestId,
                    Constants.REQUEST_STATUS_REJECTED);

            String issuerId = updatedPaymentRequest.getParticipant().split(" ")[0];

            FastpayUser issuer = userManager.getFastpayUser(issuerId);

            PaymentRequest issuerPaymentRequest = paymentRequestService.getRequestByUserId(issuer,
                    updatedPaymentRequest.getRequestDate());
            paymentRequestService.updatePaymentRequestStatus(issuerPaymentRequest.getId(), Constants.REQUEST_STATUS_REJECTED);
        } catch (UserNotFoundException unfe) {
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, unfe.getMessage());
        } catch (PaymentRequestNotFoundException prnfe) {
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, prnfe.getMessage());
            throw new PaymentRequestNotFoundException();
        }
    }

    @Override
    public void approvePaymentRequest(long requestId, FastpayUser user) throws PaymentRequestNotFoundException,
            InsuficientBalanceException {
        try {
            PaymentRequest approvableRequest = paymentRequestService.updatePaymentRequestStatus(requestId,
                    Constants.REQUEST_STATUS_ACCEPTED);
            

            String issuerId = approvableRequest.getParticipant().split(" ")[0];
            
            FastpayUser issuer = userManager.getFastpayUser(issuerId);

            PaymentRequest issuerPaymentRequest = paymentRequestService.getRequestByUserId(issuer,
                    approvableRequest.getRequestDate());
            paymentRequestService.updatePaymentRequestStatus(issuerPaymentRequest.getId(), Constants.REQUEST_STATUS_ACCEPTED);
                    
            paymentService.sendMoney(issuerId, user, approvableRequest.getRequestAmount(),
                    "REQUEST APROVAL: " + approvableRequest.getDescription());
            
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
