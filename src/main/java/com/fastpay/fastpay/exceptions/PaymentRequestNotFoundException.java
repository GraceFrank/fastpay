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
public class PaymentRequestNotFoundException extends Exception {

     private String message;
     
    public PaymentRequestNotFoundException() {
        this.message = "Payment Request Not Found";
    }

    /**
     * Constructs an instance of <code>UserNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    
    public PaymentRequestNotFoundException(String msg) {
        super(msg);
    }
    
     @Override

    public String getMessage() {
        return this.message;
    }
}

    
    