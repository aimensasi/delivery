/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.User;
import com.blank.delivery.utils.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
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
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

  

  @PersistenceContext(unitName = "com.blank.delivery_Delivery-Maven_war_0.0.1PU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public UserFacade() {
    super(User.class);
  }
  
  @Override
  public Boolean isUniqueEmail(String email) {
    TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email);
    
    return query.getResultList().isEmpty();
  }

  @Override
  public Boolean ManagementStaffExists() {
    TypedQuery<User> query = em.createNamedQuery("User.findByRole", User.class).setParameter("role", Constants.ROLE_MANAGEMENT_STAFF);
    
    return !query.getResultList().isEmpty();
  }

  @Override
  public User attempt(String email, String password) {
    TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email);
    
    List<User> users = query.getResultList();
    
    if (users.isEmpty()) {
      return null;
    }
    
    User user = users.get(0);
    
    if (user.getPassword().equals(password)) {
      return user;
    }
    
    return null;
  }
  
  
  
  
  
}
