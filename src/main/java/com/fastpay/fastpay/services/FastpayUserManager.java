/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.models.FastpayUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author grace.frank
 */

@Local
public interface FastpayUserManager {
     public FastpayUser getFastpayUser(String userId) throws Exception;
//    public List<FastpayUser> getFastpayUsers();        
    public FastpayUser registerFastpayUser(FastpayUser user) throws Exception;
    public void deleteFastpayUser(String userId) throws Exception;   
    
}