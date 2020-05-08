/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ejb.Stateless;


/**
 *
 * @author grace.frank
 */
@Stateless
public class TimeStamp {
    
    
    public String getCurrentTime(){
    LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = timestamp.format(myFormatObj);
        return (String)formattedDate;
    }
}
