/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.exceptions;

/**
 *
 * @author grace.frank
 */
import javax.ejb.ApplicationException;
 
@ApplicationException(rollback=true)
public class UserNotFoundException extends Exception {

     private String message;
     
    public UserNotFoundException() {
        this.message = "User already exists";
    }

    /**
     * Constructs an instance of <code>UserNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    
    public UserNotFoundException(String msg) {
        super(msg);
    }
    
     @Override

    public String getMessage() {
        return this.message;
    }
}

    
    