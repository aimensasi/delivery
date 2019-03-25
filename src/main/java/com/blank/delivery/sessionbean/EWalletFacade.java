/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.EWallet;
import com.blank.delivery.models.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aimen.s.a.sasi
 */
@Stateless
public class EWalletFacade extends AbstractFacade<EWallet> implements EWalletFacadeLocal {

  @PersistenceContext(unitName = "com.blank.delivery_Delivery-Maven_war_0.0.1PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public EWalletFacade() {
    super(EWallet.class);
  }

  @Override
  public EWallet findByUserId(int id) {
    TypedQuery<EWallet> query = em.createNamedQuery("EWallet.findByUserId", EWallet.class).setParameter("customer_id", id);
    
    return query.getResultList().get(0);
  }
  
  
  
}
