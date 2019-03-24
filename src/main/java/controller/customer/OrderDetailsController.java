/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import com.blank.delivery.models.Feedback;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.FeedbackFacadeLocal;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "orderDetailsController")
@RequestScoped
public class OrderDetailsController {
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  @EJB private UserFacadeLocal userFacade;
  
  @EJB
  private FeedbackFacadeLocal feedbackFacade;

   //   order id from request param
  private String id;
  private Reservation reservation;
  private List<ReservationItem> reservationItemList;
  private User currentCustomer;
  private User deliveryStaff;
  private Feedback feedback;
  /**
   * Creates a new instance of OrderDetailsController
   */
  public OrderDetailsController() {
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
    currentCustomer = reservation.getCustomer();
    deliveryStaff = reservation.getDeliveryStaff();
    
   
    if (reservation.getStatus().equals("delivered")) {
      feedback = feedbackFacade.getOrCreate(currentCustomer, reservation);
      feedback.setCreatedBy(Constants.ROLE_CUSTOMER);
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

  public User getCurrentCustomer() {
    return currentCustomer;
  }

  public void setCurrentCustomer(User currentCustomer) {
    this.currentCustomer = currentCustomer;
  }

  public User getDeliveryStaff() {
    return deliveryStaff;
  }

  public void setDeliveryStaff(User deliveryStaff) {
    this.deliveryStaff = deliveryStaff;
  }

  public Feedback getFeedback() {
    return feedback;
  }

  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }
  
  
  public void leaveFeedback(){
    if (feedback.getReceivedBy() == 0) {
      feedback.setReceivedBy(deliveryStaff.getId());
      feedbackFacade.create(feedback);
    }else{
      feedbackFacade.edit(feedback);
    }
      
    redirectBack();
  }  
  
  private void redirectBack(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/orders");
    } catch (IOException e) {

    }
  }
  
}
