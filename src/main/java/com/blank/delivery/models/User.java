/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimen.s.a.sasi
 */
@Entity
@Table(name = "user", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
  , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
  , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
  , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
  , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
  , @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")
  , @NamedQuery(name = "User.findByRoleExceptCurrent", query = "SELECT u FROM User u WHERE u.id != :id AND u.role = :role")
})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  
  
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "email", nullable = false, length = 255)
  private String email;
  
  
  @Size(max = 255)
  @Column(name = "password", length = 255)
  private String password;
  
  
  @Size(max = 255)
  @Column(name = "role", length = 255)
  private String role;
  
  @OneToMany(mappedBy = "customer")
  private List<Reservation> reservations;
  
  @OneToMany(mappedBy = "deliveryStaff")
  private List<Reservation> deliveryOrders;
  
  
  @OneToOne(mappedBy = "customer")
  private EWallet eWallet;
  

  public User() {
    
  }

  public User(Integer id) {
    this.id = id;
  }

  public User(Integer id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public EWallet geteWallet() {
    return eWallet;
  }

  public void seteWallet(EWallet eWallet) {
    this.eWallet = eWallet;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.blank.delivery.models.User[ id=" + id + " ]";
  }
  
}
