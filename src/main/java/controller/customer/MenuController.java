/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import com.blank.delivery.models.EWallet;
import com.blank.delivery.models.Food;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.EWalletFacadeLocal;
import com.blank.delivery.sessionbean.FoodFacadeLocal;
import com.blank.delivery.sessionbean.ReservationFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "menuController")
@RequestScoped
public class MenuController implements Serializable {
  
  @EJB
  private FoodFacadeLocal foodFacade;
  @EJB
  private UserFacadeLocal userFacade;
  @EJB 
  private ReservationFacadeLocal reservationFacade;
  @EJB
  private EWalletFacadeLocal eWalletFacade;
  
  
  
  
  private Reservation reservation;
  private User currentCustomer;
  
  private EWallet eWallet;
 
  private List<Food> foodList;
  private List<ReservationItem> reservationItems = new ArrayList<>();
  
  
  
  
  /**
   * Creates a new instance of Menu
   */
  public MenuController() {
  }
  
  @PostConstruct
  public void onInit(){
    currentCustomer = userFacade.find(SessionUtil.getUserId());
    eWallet = eWalletFacade.findByUserId(currentCustomer.getId());
    
    reservation = new Reservation(currentCustomer);
    
    
    foodList = foodFacade.whereQuantityMoreThanZero();
    for(Food food: foodList){
      reservationItems.add(new ReservationItem(food, reservation));
    }
  }

  @PreDestroy
  public void onDestroy(){
    reservation = null;
    reservationItems = null;
    foodList = null;
  }

  public List<Food> getFoodList() {
    return foodList;
  }

  public void setFoodList(List<Food> foodList) {
    this.foodList = foodList;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public List<ReservationItem> getReservationItems() {
    return reservationItems;
  }

  public void setReservationItems(List<ReservationItem> reservationItems) {
    this.reservationItems = reservationItems;
  }
  
  
  public void checkout(){
    float totalPrice = 0;
    List<ReservationItem> orderItems = new ArrayList<>();
    
    for(ReservationItem reservationItem : reservationItems){
      if (reservationItem.getQuantity() != 0) {
        Food food = reservationItem.getFood();
        ReservationItem item = reservationItem;
        food.setQuantity(food.getQuantity() - reservationItem.getQuantity());
        item.setFood(food);
        
        
        totalPrice += (food.getPrice() * reservationItem.getQuantity());
        orderItems.add(item);
      }
    }
    
    if (orderItems.isEmpty()) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "You must select items to checkout", null));
      return;
    }
    
    
  
    
    if (eWallet == null || eWallet.getBalance() < totalPrice) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Your e-wallet does not have enough money, top up and come back again.", null));
      return;
    }
    
    reservation.setStatus("processing");
    reservation.setTotalPrice(totalPrice);
    reservation.setReservationItemList(orderItems);
    reservationFacade.create(reservation);
    
    
    
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/orders");
    } catch (IOException e) {
      
    }
    
  }
}
