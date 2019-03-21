/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimen.s.a.sasi
 */
@Entity
@Table(name = "reservation_items")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ReservationItem.findAll", query = "SELECT r FROM ReservationItem r")
  , @NamedQuery(name = "ReservationItem.findByQuantity", query = "SELECT r FROM ReservationItem r WHERE r.quantity = :quantity")})
public class ReservationItem implements Serializable {

  private static final long serialVersionUID = 1L;

  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Reservation reservation;
  
  @ManyToOne
  @JoinColumn(name = "food_id")
  private Food food;
  
  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  
  
 

  public ReservationItem() {
  }
  
  public ReservationItem(Food food, Reservation reservation){
    this.food = food;
    this.reservation = reservation; 
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation Reservation) {
    this.reservation = Reservation;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }


  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof ReservationItem)) {
      return false;
    }
    ReservationItem other = (ReservationItem) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  
  @Override
  public String toString() {
    return "com.blank.delivery.models.ReservationItem[ id=" + id + " ]";
  }
}
