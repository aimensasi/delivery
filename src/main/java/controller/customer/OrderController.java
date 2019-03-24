/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "orderController")
@SessionScoped
public class OrderController implements Serializable {
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  @EJB
  private UserFacadeLocal userFacade;
  
  private String feedbackStatus = "delivered";
  
  private List<Reservation> reservations;
  

  /**
   * Creates a new instance of OrderController
   */
  public OrderController() {
  }
  
  @PostConstruct
  public void onInit(){
    User currentCustomer = userFacade.find(SessionUtil.getUserId());
    reservations = currentCustomer.getReservations();
  }

  @PreDestroy
  public void onDestroy(){
    reservations = null;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public String getFeedbackStatus() {
    return feedbackStatus;
  }

  public void setFeedbackStatus(String feedbackStatus) {
    this.feedbackStatus = feedbackStatus;
  }
  
  
  public void view(Reservation reservation){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/orders/" + reservation.getId() );
    } catch (IOException e) {
      
    }
  }
 
  
  
}
