/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reservation;

import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import com.blank.delivery.utils.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "showOrderController")
@RequestScoped
public class ShowOrderController implements Serializable {
  
  
  @EJB
  private ReservationFacadeLocal reservationFacade;
  
  @EJB private UserFacadeLocal userFacade;

//   order id from request param
  private String id;
  private Reservation reservation;
  private List<ReservationItem> reservationItemList;
  private User customer;
  private List<User> deliveryStaffList;
  private User selectedDeliveryStaff;
   
   
  /**
   * Creates a new instance of ShowOrderController
   */
  public ShowOrderController() {
   
  }
  
  
  @PostConstruct
  public void onInit(){
    System.out.println("controller.reservation.ShowOrderController.onInit()");
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
    
    
    selectedDeliveryStaff = reservation.getDeliveryStaff();
    
    setAvailableDeliveryStaff();
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

  public List<User> getDeliveryStaffList() {
    return deliveryStaffList;
  }

  public void setDeliveryStaffList(List<User> deliveryStaffList) {
    this.deliveryStaffList = deliveryStaffList;
  }

  public User getSelectedDeliveryStaff() {
    return selectedDeliveryStaff;
  }

  public void setSelectedDeliveryStaff(User selectedDeliveryStaff) {
    this.selectedDeliveryStaff = selectedDeliveryStaff;
  }
  
  
  
 
  public void approve(User deliveryStaff){
    reservation.setDeliveryStaff(deliveryStaff);
    reservation.setStatus("approved");
    
    reservationFacade.edit(reservation);
    
    refresh();
  }
  
  public void reject(){
    if (reservation.getStatus().equals("approved")) {
      reservation.setDeliveryStaff(null);
    }else if(reservation.getStatus().equals("rejected")){
      return;
    }
    
    reservation.setStatus("rejected");
    reservationFacade.edit(reservation);
    
    refresh();
  }
    
  
  private void refresh(){
//    onInit();
    
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/reservation/orders/" + id + "?faces-redirect=true");
    } catch (IOException e) {

    }
  }
  
  
  private void redirectBack(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/reservation/orders");
    } catch (IOException e) {

    }
  }  
  
  private void setAvailableDeliveryStaff(){
    List<User> availableDelivList = new ArrayList<>();
    deliveryStaffList = userFacade.findByRole(Constants.ROLE_DELIVERY_STAFF); 
    
    
    for(User u : deliveryStaffList){
      if (reservationFacade.findApprovedOrdersWithDeliverStaff(u).isEmpty()) {
       availableDelivList.add(u);
      }
    }
    
    deliveryStaffList = availableDelivList;
  }
    

}

