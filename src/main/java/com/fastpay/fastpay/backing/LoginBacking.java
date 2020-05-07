/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.services.FastpayUserManager;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author grace.frank
 */
@Named
@SessionScoped
public class LoginBacking extends BaseBacking implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    @EJB
    private FastpayUserManager userManager;

    private String password;
    private String msg;
    private String userId;
    private FastpayUser user;

    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public FastpayUser getUser() {
        return user;
    }

    public void setUser(FastpayUser user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        FastpayUser validUser = userManager.validateUser(userId, password);
        if (validUser != null) {
            HttpSession session = getSession();
            session.setAttribute("user", validUser);
            user = validUser;
            return "success";
        } else {
            getContext().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = getSession();
        session.invalidate();
        return "login";
    }
}
