/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.utils;

import com.blank.delivery.sessionbean.UserFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "authUtil")
@SessionScoped
public class AuthUtil implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;

  /**
   * Creates a new instance of AuthUtil
   */
  public AuthUtil() {
  }
  
  
  public void isGuest(){
    Object userIdSession = SessionUtil.getAttribute(Constants.USER_ID);
    Object roleTypeSession = SessionUtil.getAttribute(Constants.ROLE_TYPE);
    
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    
    if (userIdSession != null) {
      switch(roleTypeSession.toString()){
        case Constants.ROLE_MANAGEMENT_STAFF:
          
          try {
            context.redirect(context.getRequestContextPath() + "/management/dashboard?faces-redirect=true");
          } catch (IOException e) {
            System.out.println("com.blank.delivery.utils.AuthUtil.isGuest()  " + e.getMessage());
          }
          
          break;
        case Constants.ROLE_RESERVATION_STAFF:
          break;
        case Constants.ROLE_DELIVERY_STAFF:
          break;
        case Constants.ROLE_CUSTOMER:
          break;
      }
    }
  } 
  
  
  public void isOpen(){
    isGuest();
    
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    
    if (userFacade.ManagementStaffExists()) {
      try {
        context.redirect(context.getRequestContextPath() + "/?faces-redirect=true");
      } catch (IOException e) {
        System.out.println("com.blank.delivery.utils.AuthUtil.isGuest()  " + e.getMessage());
      }
    }
  }
  
  public void isAuthenticated(){
    Object userIdSession = SessionUtil.getAttribute(Constants.USER_ID);
    
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    
    if (userIdSession == null) {
      try {
        context.redirect(context.getRequestContextPath() + "/login?faces-redirect=true");
      } catch (IOException e) {
        System.out.println("com.blank.delivery.utils.AuthUtil.isGuest()  " + e.getMessage());
      }
    }
  }
}
