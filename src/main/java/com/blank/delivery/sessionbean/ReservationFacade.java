/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Food;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

  @PersistenceContext(unitName = "com.blank.delivery_Delivery-Maven_war_0.0.1PU")
  private EntityManager em;
  
  @EJB
  private FoodFacadeLocal foodFacade;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ReservationFacade() {
    super(Reservation.class);
  } 

  @Override
  public void create(Reservation entity) {
    super.create(entity); //To change body of generated methods, choose Tools | Templates.
    
    for(ReservationItem reservationItem : entity.getReservationItemList()){
      foodFacade.edit(reservationItem.getFood());
    }
  }

  @Override
  public List<Reservation> getCurrentCustomerOrders() {
    return null;
  }
  
  
  
  
}