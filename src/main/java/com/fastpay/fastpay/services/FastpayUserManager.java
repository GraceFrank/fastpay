/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.services;

import com.fastpay.fastpay.exceptions.UserAlreadyExistException;
import com.fastpay.fastpay.exceptions.UserNotFoundException;
import com.fastpay.fastpay.models.FastpayUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author grace.frank
 */
@Local
public interface FastpayUserManager {

    public FastpayUser getFastpayUser(String userId) throws UserNotFoundException;
//    public List<FastpayUser> getFastpayUsers();        

    public FastpayUser registerFastpayUser(FastpayUser user) throws UserAlreadyExistException;

    public void deleteFastpayUser(String userId) throws Exception;

    public FastpayUser validateUser(String user, String pwd);

    public void updateUser(FastpayUser user) throws UserNotFoundException;
    
}
