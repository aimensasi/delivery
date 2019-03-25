/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimen.s.a.sasi
 */
@Entity
@Table(name = "e_wallets")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "EWallet.findAll", query = "SELECT e FROM EWallet e"),
  @NamedQuery(name = "EWallet.findByUserId", query = "SELECT e FROM EWallet e WHERE e.customerId = :customer_id")
})
public class EWallet implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  
 @OneToOne(cascade = CascadeType.REFRESH)
 @JoinColumn(name = "customer_id")
 private User customer;
 
 @Column(name = "customer_id", insertable = false, updatable = false)
 private int customerId;
  
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "balance")
  private Float balance;

  public EWallet() {
  }

  public EWallet(Integer id) {
    this.id = id;
  }

  public EWallet(User customer) {
    this.customer = customer;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
    this.customer = customer;
  }

  public Float getBalance() {
    return balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
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
    if (!(object instanceof EWallet)) {
      return false;
    }
    EWallet other = (EWallet) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.blank.delivery.models.EWallet[ id=" + id + "  balance" +  balance +" ]";
  }
  
}
