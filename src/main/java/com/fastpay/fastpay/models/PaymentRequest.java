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
 * @author Iben Labaran
 */
@Entity
@Table(name = "PAYMENT_REQUESTS")
public class PaymentRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "REQUEST_AMOUNT")
    private double requestAmount;

    @Basic(optional = false)
    @Column(name = "REQUEST_TYPE")
    private String requestType;

    @Basic(optional = false)
    @Column(name = "REQUEST_STATUS")
    private String requestStatus;

    @Basic(optional = false)
    @Column(name = "REQUEST_DATE")
    private String requestDate;

    @Basic(optional = false)
    @Column(name = "PARTICIPANT")
    private String Participant;
    
     @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FastpayUser userId;

    //CONSTRUCTORS
    public PaymentRequest() {
    }

    public PaymentRequest(Long id) {
        this.id = id;
    }

    public PaymentRequest(Long id, double requestAmount, String type, String requestStatus, 
                    String requestDate, String Participant, FastpayUser userId, String description) {
        this.id = id;
        this.requestAmount = requestAmount;
        this.requestType = type;
        this.requestStatus = requestStatus;
        this.requestDate = requestDate;
        this.Participant = Participant;
        this.userId = userId;
        this.description = description;
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String Participant) {
        this.Participant = Participant;
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
        if (!(object instanceof PaymentRequest)) {
            return false;
        }
        PaymentRequest other = (PaymentRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fastpay.fastpay.models.PaymentRequest[ id=" + id + " ]";
    }

}
