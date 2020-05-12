/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.PaymentRequestNotFoundException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.Constants;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.models.PaymentRequest;
import com.fastpay.fastpay.utils.TimeStamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Iben Labaran
 */
@Stateless
public class PaymentRequestServiceImpl implements PaymentRequestService {

    @PersistenceContext(unitName = "fastpayUnit")
    EntityManager em;

    @EJB
    private FastpayUserManager userManager;

    @EJB
    private TimeStamp timestamp;

    @Override
    public void createPaymentRequest(String userId, String participant, double amount, String type, String description,
            String requestDate) throws UserNotFoundException {

        try {
            FastpayUser confirmedUser = userManager.getFastpayUser(userId);

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setRequestAmount(amount);
            paymentRequest.setRequestType(type);
            paymentRequest.setRequestStatus(Constants.REQUEST_STATUS_PENDING);
            paymentRequest.setRequestDate(requestDate);
            paymentRequest.setParticipant(participant);
            paymentRequest.setUserId(confirmedUser);
            paymentRequest.setDescription(description);

            em.persist(paymentRequest);

            em.flush();

        } catch (UserNotFoundException uae) {
            throw new UserNotFoundException();
        }// catch (Exception e) {
//            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, e.toString());
//
//        }

    }

    @Override
    public PaymentRequest updatePaymentRequestStatus(long requestId, String status) throws PaymentRequestNotFoundException {
        PaymentRequest updatablePaymentRequest = em.find(PaymentRequest.class, requestId);

        if (updatablePaymentRequest == null) {
            throw new PaymentRequestNotFoundException();
        }
        updatablePaymentRequest.setRequestStatus(status); //approved status
        em.merge(updatablePaymentRequest);
        em.flush();
        return updatablePaymentRequest;
    }

    @Override
    public List<PaymentRequest> viewPendingRequests(FastpayUser user, String type) {
        Query query = em.createQuery("select request from PaymentRequest request where "
                + "request.userId = :user and request.requestType = :type and request.requestStatus = :status");

        query.setParameter("user", user);
        query.setParameter("type", type);
        query.setParameter("status", Constants.REQUEST_STATUS_PENDING);

        List<PaymentRequest> result = query.getResultList();
        if (result != null && result.size() > 0) {
            return result;
        }

        return null;

    }

    @Override
    public List<PaymentRequest> viewCompletedRequests(FastpayUser user) {
        Query query = em.createQuery("select request from PaymentRequest request where "
                + "request.userId=:user and request.requestStatus != :status");

        query.setParameter("user", user);
        query.setParameter("status", Constants.REQUEST_STATUS_PENDING);

        List<PaymentRequest> result = query.getResultList();
        if (result != null && result.size() > 0) {
            Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, result.get(0).getRequestDate() + "RESULT found");
            return result;
        }
        Logger.getLogger(RequestManagerService.class.getName()).log(Level.SEVERE, "No RESULT");

        return null;
    }

    @Override
    public PaymentRequest getRequestByUserId(FastpayUser user, String requestDate) throws PaymentRequestNotFoundException {
        Query query = em.createQuery("select a from PaymentRequest a where "
                + "a.userId = :userID and a.requestDate = :requestDate");

        query.setParameter("userID", user);
        query.setParameter("requestDate", requestDate);

        List<PaymentRequest> result = query.getResultList();

        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            throw new PaymentRequestNotFoundException();
        }
    }

}
