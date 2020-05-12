/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Iben Labaran
 */
@ApplicationException(rollback=true)
public class InvalidRequestException extends Exception{
    
     private String message;
     
    /**
     * Creates a new instance of <code>UserAlreadyExistException</code> without
     * detail message.
     */
    public InvalidRequestException() {
           this.message = "Error Invalid  Request";
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidRequestException(String msg) {
        super(msg);
    }
    
     @Override

    public String getMessage() {
        return this.message;
    }
    
}
