/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reservation;

import com.blank.delivery.models.Reservation;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "rOrderController")
@SessionScoped
public class OrderController implements Serializable {
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  private List<Reservation> reservationList;

  /**
   * Creates a new instance of OrderController
   */
  public OrderController() {
  }
  
  @PostConstruct
  public void onInit(){
    reservationList = reservationFacade.findAll();
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
      context.redirect(context.getRequestContextPath()+ "/reservation/orders/" + reservation.getId());
    } catch (IOException e) {
      
    }
  }
}
