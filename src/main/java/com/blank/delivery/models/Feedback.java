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
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
  , @NamedQuery(name = "Feedback.findById", query = "SELECT f FROM Feedback f WHERE f.id = :id")
  , @NamedQuery(name = "Feedback.findByGivenBy", query = "SELECT f FROM Feedback f WHERE f.givenBy = :givenBy")
  , @NamedQuery(name = "Feedback.findByReceivedBy", query = "SELECT f FROM Feedback f WHERE f.receivedBy = :receivedBy")
  , @NamedQuery(name = "Feedback.findByRating", query = "SELECT f FROM Feedback f WHERE f.rating = :rating")})
public class Feedback implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "given_by")
  private int givenBy;
  @Basic(optional = false)
  @NotNull
  @Column(name = "received_by")
  private int receivedBy;
  @Column(name = "rating")
  private Integer rating;
  @Basic(optional = false)
  @NotNull
  @Lob
  @Size(min = 1, max = 65535)
  @Column(name = "feedback")
  private String feedback;

  public Feedback() {
  }

  public Feedback(Integer id) {
    this.id = id;
  }

  public Feedback(Integer id, int givenBy, int receivedBy, String feedback) {
    this.id = id;
    this.givenBy = givenBy;
    this.receivedBy = receivedBy;
    this.feedback = feedback;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getGivenBy() {
    return givenBy;
  }

  public void setGivenBy(int givenBy) {
    this.givenBy = givenBy;
  }

  public int getReceivedBy() {
    return receivedBy;
  }

  public void setReceivedBy(int receivedBy) {
    this.receivedBy = receivedBy;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
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
    if (!(object instanceof Feedback)) {
      return false;
    }
    Feedback other = (Feedback) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.blank.delivery.models.Feedback[ id=" + id + " ]";
  }
  
}
