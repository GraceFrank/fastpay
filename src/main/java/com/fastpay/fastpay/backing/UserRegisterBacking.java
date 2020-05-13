/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.exceptions.UserAlreadyExistException;
import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.services.FastpayUserManager;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Iben Labaran
 */
@Named(value = "userRegisterBacking")
@ViewScoped
public class UserRegisterBacking extends BaseBacking implements Serializable {

    @EJB
    private FastpayUserManager userManager;

    @Named(value = "newUser")
    @Produces
    @RequestScoped
    private FastpayUser newUser = new FastpayUser();

    private String infoMessage;
 

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String registerUser() {
        try {
            userManager.registerFastpayUser(newUser);
            infoMessage = "User saved sucessfully";
            newUser = new FastpayUser();
            return "success";
        }catch(UserAlreadyExistException uaeEx){
             Logger.getLogger(UserRegisterBacking.class.getName()).log(Level.SEVERE, null, uaeEx);
            infoMessage = "User Email already in use";
        } catch (Exception e) {
            Logger.getLogger(UserRegisterBacking.class.getName()).log(Level.SEVERE, null, e);
                getContext().addMessage(null, new FacesMessage("An error occurs while registering user"));  
        }

        return null;
    }

}
