/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import com.blank.delivery.models.Food;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

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
  
  private List<Reservation> reservations;
  

  /**
   * Creates a new instance of OrderController
   */
  public OrderController() {
  }
  
  @PostConstruct
  public void onInit(){
    User currentCustomer = userFacade.find(SessionUtil.getUserId());
    
    System.out.println("controller.customer.OrderController.onInit() " + currentCustomer.getEmail());
    reservations = currentCustomer.getReservations();
    
    System.out.println("controller.customer.OrderController.onInit() " + reservations.size());
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
 
  
  
}
