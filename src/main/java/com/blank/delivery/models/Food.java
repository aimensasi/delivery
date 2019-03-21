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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimen.s.a.sasi
 */
@Entity
@Table(name = "food")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
  , @NamedQuery(name = "Food.findById", query = "SELECT f FROM Food f WHERE f.id = :id")
  , @NamedQuery(name = "Food.findByName", query = "SELECT f FROM Food f WHERE f.name = :name")
  , @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category")
  , @NamedQuery(name = "Food.findByQuantity", query = "SELECT f FROM Food f WHERE f.quantity = :quantity")
  , @NamedQuery(name = "Food.findByPrice", query = "SELECT f FROM Food f WHERE f.price = :price")
  , @NamedQuery(name = "Food.whereQuantityNotZero", query = "SELECT f FROM Food f WHERE f.quantity > 0")
        
 })
public class Food implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name")
  private String name;
  @Lob
  @Size(max = 65535)
  @Column(name = "description")
  private String description;
  @Size(max = 255)
  @Column(name = "category")
  private String category;
  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "price")
  private Float price;

  public Food() {
  }

  public Food(Integer id) {
    this.id = id;
  }

  public Food(Integer id, String name, int quantity) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
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
    if (!(object instanceof Food)) {
      return false;
    }
    Food other = (Food) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.blank.delivery.models.Food[ id=" + id + " ]";
  }
  
}
