/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author grace.frank
 */
@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Basic(optional = false)
    @Column(name = "TRANSACTION_AMOUNT")
    private double transactionAmount;

    @Basic(optional = false)
    @Column(name = "TRANSACTION_DATE")
    private String transactionDate;

    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(optional = false)
    @Column(name = "PARTICIPANT")
    private String participant;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FastpayUser userId;

    //CONSTRUCTORS
    public PaymentTransaction() {
    }

    public PaymentTransaction(Long id) {
        this.id = id;
    }

    public PaymentTransaction(Long id, double transactionAmount, FastpayUser userId, String transactionDate, String participant, String description) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.participant = participant;
        this.description = description;
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public FastpayUser getUserId() {
        return userId;
    }

    public void setUserId(FastpayUser userId) {
        this.userId = userId;
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
        if (!(object instanceof PaymentTransaction)) {
            return false;
        }
        PaymentTransaction other = (PaymentTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fastpay.fastpay.models.PaymentTransaction[ id=" + id + " ]";
    }

}
