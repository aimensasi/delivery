/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Food;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import com.blank.delivery.utils.Constants;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

  @Override
  public List<Reservation> findApprovedOrdersWithDeliverStaff(User deliveryStaff) {
    TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findApprovedByDeliveryStaff", Reservation.class).setParameter("delivery_staff_id", deliveryStaff.getId()).setParameter("status", "approved");
    
    return query.getResultList();
  }

  @Override
  public List<Reservation> findDeliveredOrdersWithDeliverStaff(User deliveryStaff) {
    TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findDeliveredByDeliveryStaff", Reservation.class).setParameter("delivery_staff_id", deliveryStaff.getId()).setParameter("status", "delivered");
    
    return query.getResultList();
  }
  
  
}
