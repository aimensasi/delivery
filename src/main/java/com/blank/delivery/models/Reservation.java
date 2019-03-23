/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimen.s.a.sasi
 */
@Entity
@Table(name = "reservations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
  , @NamedQuery(name = "Reservation.findApprovedByDeliveryStaff", query = "SELECT r FROM Reservation r WHERE r.deliverStaffId = :delivery_staff_id AND r.status = :status")
  , @NamedQuery(name = "Reservation.findDeliveredByDeliveryStaff", query = "SELECT r FROM Reservation r WHERE r.deliverStaffId = :delivery_staff_id AND r.status = :status")
})
public class Reservation implements Serializable {

  private static final long serialVersionUID = 1L;
  
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private User customer;
  
  @ManyToOne
  @JoinColumn(name = "delivery_staff_id")
  private User deliveryStaff;
  
  @Column(name = "delivery_staff_id", insertable = false, updatable = false)
  private int deliverStaffId;
  
  @Basic(optional = false)
  @Size(min = 1, max = 255)
  @Column(name = "status")
  private String status;
  
  @Basic(optional = false)
  @NotNull
  @Column(name = "total_price")
  private float totalPrice;
  
  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "order_id")
  private List<ReservationItem> reservationItemList = new ArrayList<>();
  

  public Reservation() {
  }

  public Reservation(Integer id) {
    this.id = id;
  }

  public Reservation(Integer id, User customer, User deliveryStaff, String status, float totalPrice) {
    this.id = id;
    this.customer = customer;
    this.deliveryStaff = deliveryStaff;
    this.status = status;
    this.totalPrice = totalPrice;
  }
  
  public Reservation(User customer) {
    this.customer = customer;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
    this.customer = customer;
  }

  public User getDeliveryStaff() {
    return deliveryStaff;
  }

  public void setDeliveryStaff(User deliveryStaff) {
    this.deliveryStaff = deliveryStaff;
  }

  public List<ReservationItem> getReservationItemList() {
    return reservationItemList;
  }

  public void setReservationItemList(List<ReservationItem> reservationItemList) {
    this.reservationItemList = reservationItemList;
  }

  public int getDeliverStaffId() {
    return deliverStaffId;
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
    if (!(object instanceof Reservation)) {
      return false;
    }
    Reservation other = (Reservation) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.blank.delivery.models.Reservation[ id=" + id + " ]";
  }
  
}
