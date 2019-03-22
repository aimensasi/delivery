/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.EWallet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
  
}
