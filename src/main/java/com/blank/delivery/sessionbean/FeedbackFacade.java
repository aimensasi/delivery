/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Feedback;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aimen.s.a.sasi
 */
@Stateless
public class FeedbackFacade extends AbstractFacade<Feedback> implements FeedbackFacadeLocal {

  @PersistenceContext(unitName = "com.blank.delivery_Delivery-Maven_war_0.0.1PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public FeedbackFacade() {
    super(Feedback.class);
  }

  @Override
  public Feedback getOrCreate(User user, Reservation reservation) {
    TypedQuery<Feedback> query = em.createNamedQuery("Feedback.findByOrderAndGiven", Feedback.class).setParameter("orderId", reservation.getId()).setParameter("userId", user.getId());
    
    Feedback feedback = query.getResultList().isEmpty() ? null : query.getResultList().get(0) ;
    
    if (feedback == null) {
      feedback = new Feedback();
      feedback.setGivenBy(user.getId());
      feedback.setOrderId(reservation.getId());
    }
    return feedback;
  }

  @Override
  public List<Feedback> findByReceivedBy(User user) {
    TypedQuery<Feedback> query = em.createNamedQuery("Feedback.findByReceivedBy", Feedback.class).setParameter("receivedBy", user.getId());
    
    return query.getResultList();
  }
  
  
  
}
