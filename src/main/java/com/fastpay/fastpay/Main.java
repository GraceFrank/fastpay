/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay;

import com.fastpay.fastpay.models.FastpayUser;
import com.fastpay.fastpay.services.FastpayUserManager;
import com.fastpay.fastpay.services.FastpayUserManagerImpl;
import javax.ejb.EJB;

/**
 *
 * @author grace.frank
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         FastpayUserManager userManager = new FastpayUserManagerImpl();
         
         FastpayUser user = new FastpayUser("frankgrace@y.com", "firstName", "lastName", "password", "dollar", 3000);
         
         try{
         userManager.registerFastpayUser(user);
         }catch(Exception e){
             System.out.println("error saving data");
         }
         
         FastpayUser newUser; 
                 try{
               newUser = (FastpayUser)  userManager.getFastpayUser("frankgrace@y.com");
               System.out.println(newUser.getId());
                 }catch(Exception e){
                     System.out.println(e.getMessage());
                 }
    }
    
}
