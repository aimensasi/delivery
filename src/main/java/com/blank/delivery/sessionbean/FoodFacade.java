/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Food;
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
public class FoodFacade extends AbstractFacade<Food> implements FoodFacadeLocal {

  @PersistenceContext(unitName = "com.blank.delivery_Delivery-Maven_war_0.0.1PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public FoodFacade() {
    super(Food.class);
  }

  @Override
  public List<Food> whereQuantityMoreThanZero() {
    
    TypedQuery<Food> query = em.createNamedQuery("Food.whereQuantityNotZero", Food.class);
    
    return query.getResultList();
  }

  
  
  
  
}
