/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
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
@Named(value = "dashboardController")
@SessionScoped
public class DashboardController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  
  
  private int customerCount;
  private int deliveryStaffCount;
  private int reservationStaffCount;
  private int reservationsCount;
  

  /**
   * Creates a new instance of DashboardController
   */
  public DashboardController() {
  }
  
  
  @PostConstruct
  public void onInit(){
    customerCount = userFacade.findByRole(Constants.ROLE_CUSTOMER).size();
    reservationStaffCount = userFacade.findByRole(Constants.ROLE_RESERVATION_STAFF).size();
    deliveryStaffCount = userFacade.findByRole(Constants.ROLE_DELIVERY_STAFF).size();
    reservationsCount = reservationFacade.findAll().size();
  }

  @PreDestroy
  public void onDestroy(){
 
  }

  public int getCustomerCount() {
    return customerCount;
  }

  public void setCustomerCount(int customerCount) {
    this.customerCount = customerCount;
  }

  public int getDeliveryStaffCount() {
    return deliveryStaffCount;
  }

  public void setDeliveryStaffCount(int deliveryStaffCount) {
    this.deliveryStaffCount = deliveryStaffCount;
  }

  public int getReservationStaffCount() {
    return reservationStaffCount;
  }

  public void setReservationStaffCount(int reservationStaffCount) {
    this.reservationStaffCount = reservationStaffCount;
  }

  public int getReservationsCount() {
    return reservationsCount;
  }

  public void setReservationsCount(int reservationsCount) {
    this.reservationsCount = reservationsCount;
  }
  
  
  
  
  
  public String getCurrentUserName(){
    int id = SessionUtil.getUserId();
    
    if (id == -1) {
      return "User Profile";
    }
    User user = userFacade.find(id);
    
    return user.getName();
  }
  
}
