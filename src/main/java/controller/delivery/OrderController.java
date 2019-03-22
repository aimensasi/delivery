/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.delivery;

import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.SessionUtil;
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
@Named(value = "dOrderController")
@RequestScoped
public class OrderController {
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  @EJB
  private UserFacadeLocal userFacade;
  
  private List<Reservation> reservationList;
  
  private User currentDeliverystaff;
  

  /**
   * Creates a new instance of OrderController
   */
  public OrderController() {
  }
  
  @PostConstruct
  public void onInit(){
    int id = SessionUtil.getUserId();
    
    currentDeliverystaff = userFacade.find(id);
    
    reservationList = reservationFacade.findApprovedOrdersWithDeliverStaff(currentDeliverystaff);
  }

  @PreDestroy
  public void onDestroy(){
    reservationList = null;
  }

  public List<Reservation> getReservationList() {
    return reservationList;
  }

  public void setReservationList(List<Reservation> reservationList) {
    this.reservationList = reservationList;
  }
  
  
  public void view(Reservation reservation){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/delivery/orders/" + reservation.getId());
    } catch (IOException e) {
      
    }
  }
  
}
