/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Iben Labaran
 */
@Entity
@Table(name = "FASTPAY_USER")
public class FastpayUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;

    @Basic(optional = false)
    @Column(name = "ACCOUNT_BALANCE")
    private double accountBalance;

    @Basic(optional = false)
    @Column(name = "CURRENCY")
    private String currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<PaymentTransaction> paymentTransactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<PaymentRequest> paymentRequests;

    //Constructor
    public FastpayUser() {
    }

    public FastpayUser(String id) {
        this.id = id;
    }

    public FastpayUser(String id, String firstName, String lastName, String password, String currency) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currency = currency;
    }

    //GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }

    public List<PaymentRequest> getPaymentRequests() {
        return paymentRequests;
    }

    public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
        this.paymentRequests = paymentRequests;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FastpayUser)) {
            return false;
        }
        FastpayUser other = (FastpayUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fastpay.fastpay.models.User[ id=" + id + " ]";
    }

}
