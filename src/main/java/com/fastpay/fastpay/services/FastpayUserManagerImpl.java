/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.FastpayUser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author grace.frank
 */
@Stateless
public class FastpayUserManagerImpl implements FastpayUserManager {

    @PersistenceContext(unitName = "fastpayUnit")
    EntityManager em;

    @Override
    public FastpayUser getFastpayUser(String userId) throws Exception {

        TypedQuery<FastpayUser> query = em.createQuery("SELECT DISTINCT fastpayUser FROM FastpayUser fastpayUser WHERE fastpayUser.id = :entityId ORDER BY fastpayUser.id", FastpayUser.class);
        query.setParameter("entityId", userId);

        FastpayUser fetchedUser;

        try {
            fetchedUser = query.getSingleResult();
        } catch (NoResultException exception) {
            throw new Exception("User not found");
        }

        return fetchedUser;
    }

//    @Override
//    public List<FastpayUser> getFastpayUsers() {
//    }

    @Override
    public FastpayUser registerFastpayUser(FastpayUser user) throws Exception {
        Query query = em.createQuery("select a from FastpayUser a where a.id = :userId");
        
        query.setParameter("userId", user.getId());
        
        try{
            query.getSingleResult();
            throw new Exception("user already exists");
        }
        catch(NoResultException nre){
             System.out.println("no user found");
        }
      
        em.persist(user);  
        em.flush();        
        
        return user;
    }

    @Override
    public void deleteFastpayUser(String userId) throws Exception {
        
    }

}