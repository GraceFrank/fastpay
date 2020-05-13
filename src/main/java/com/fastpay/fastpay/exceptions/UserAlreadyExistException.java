/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.exceptions;

/**
 *
 * @author Iben Labaran
 */

import javax.ejb.ApplicationException;
 
@ApplicationException(rollback=true)
public class UserAlreadyExistException extends Exception {

     private String message;
     
    /**
     * Creates a new instance of <code>UserAlreadyExistException</code> without
     * detail message.
     */
    public UserAlreadyExistException() {
           this.message = "User already exists";
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
    
     @Override

    public String getMessage() {
        return this.message;
    }
}

