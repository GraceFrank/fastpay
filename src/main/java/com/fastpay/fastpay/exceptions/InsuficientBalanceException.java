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
public class InsuficientBalanceException extends Exception {

     private String message;
     
    /**
     * Creates a new instance of <code>UserAlreadyExistException</code> without
     * detail message.
     */
    public InsuficientBalanceException() {
           this.message = "Insufficient Account Balance";
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InsuficientBalanceException(String msg) {
        super(msg);
    }
    
     @Override

    public String getMessage() {
        return this.message;
    }
}

