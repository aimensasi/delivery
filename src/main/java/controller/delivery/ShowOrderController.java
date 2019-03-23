/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.delivery;

import com.blank.delivery.models.Feedback;
import controller.reservation.*;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.FeedbackFacadeLocal;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "dShowOrderController")
@RequestScoped
public class ShowOrderController implements Serializable {
  
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  @EJB private UserFacadeLocal userFacade;
  
  @EJB
  private FeedbackFacadeLocal feedbackFacade;

//   order id from request param
  private String id;
  private Reservation reservation;
  private List<ReservationItem> reservationItemList;
  private User customer;
  private User deliveryStaff;
  private Feedback feedback;
   
   
  /**
   * Creates a new instance of ShowOrderController
   */
  public ShowOrderController() {
   
  }
  
  
  @PostConstruct
  public void onInit(){
    id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    
    if (id == null) {
      redirectBack();
    }
    
    
    reservation = reservationFacade.find(Integer.valueOf(id));
    
    if (reservation == null) {
      redirectBack();
    }
    
    reservationItemList = reservation.getReservationItemList();
    customer = reservation.getCustomer();
    deliveryStaff = reservation.getDeliveryStaff();
    
    if (reservation.getStatus().equals("delivered")) {
      feedback = feedbackFacade.getOrCreate(deliveryStaff, reservation);
      feedback.setCreatedBy(Constants.ROLE_DELIVERY_STAFF);
    }
  }

  @PreDestroy
  public void onDestroy(){
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public List<ReservationItem> getReservationItemList() {
    return reservationItemList;
  }

  public void setReservationItemList(List<ReservationItem> reservationItemList) {
    this.reservationItemList = reservationItemList;
  }

  public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
    this.customer = customer;
  }

  public Feedback getFeedback() {
    return feedback;
  }

  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }
  
  

  
  public void delivered(){
    if (reservation.getStatus().equals("delivered")) {
      return;
    }
    reservation.setStatus("delivered");
    
    reservationFacade.edit(reservation);
    
    redirectBack();
  }
  
  public void leaveFeedback(){
    if (feedback.getReceivedBy() == 0) {
      feedback.setReceivedBy(customer.getId());
      feedbackFacade.create(feedback);
    }else{
      feedbackFacade.edit(feedback);
    }
      
    redirectBack();
  }  
    
  
  private void refresh(){    
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/delivery/orders/" + id + "?faces-redirect=true");
    } catch (IOException e) {

    }
  }
  
  
  private void redirectBack(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/delivery/orders");
    } catch (IOException e) {

    }
  }  
  
}

