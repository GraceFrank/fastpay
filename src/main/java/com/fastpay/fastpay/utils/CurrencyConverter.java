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

    private Map<String, Double> currencyPoundsRate = new HashMap<String, Double>();

    public CurrencyConverter() {
        currencyPoundsRate.put("USD", 1.23);
        currencyPoundsRate.put("GBP", 1.0);
        currencyPoundsRate.put("EUR", 1.13);

    }
    
    public double convert(String from, String to, double amount){
        double pound = amount/ currencyPoundsRate.get(from);
        return pound * currencyPoundsRate.get(to);
        }

}
