/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.utils;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author grace.frank
 */
@Stateless
public class CurrencyConverter {

    private Map<String, Double> currencyDollarRate = new HashMap<String, Double>();

    public CurrencyConverter() {
        currencyDollarRate.put("USD", 1.0);
        currencyDollarRate.put("GBP", 69.18);
        currencyDollarRate.put("AUD", 1.0);
        currencyDollarRate.put("NGN", 389.5);
        currencyDollarRate.put("EUR", 0.92);
        currencyDollarRate.put("JPY", 106.31);
        currencyDollarRate.put("CAD", 1.40);

    }
    
    public double convert(String from, String to, double amount){
        double dollar = amount/ currencyDollarRate.get(from);
        return dollar * currencyDollarRate.get(to);
        }

}
