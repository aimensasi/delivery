/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.utils;

import com.blank.delivery.models.User;
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
    
    String redirectUrl = "";
    
    if (userIdSession != null) {
      switch(roleTypeSession.toString()){
        case Constants.ROLE_MANAGEMENT_STAFF:
          redirectUrl = "/management/dashboard?faces-redirect=true";
          break;
        case Constants.ROLE_RESERVATION_STAFF:
          redirectUrl = "/reservation/dashboard?faces-redirect=true";
          break;
        case Constants.ROLE_DELIVERY_STAFF:
          redirectUrl = "/delivery/dashboard?faces-redirect=true";
          break;
        case Constants.ROLE_CUSTOMER:
          redirectUrl = "/menu?faces-redirect=true";
          break;
      }
      
      
      try {
        context.redirect(context.getRequestContextPath() + redirectUrl);
      } catch (IOException e) {
        System.out.println("com.blank.delivery.utils.AuthUtil.isGuest()  " + e.getMessage());
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
  
  public void onlyManagementStaff(){
    Object roleTypeSession = SessionUtil.getAttribute(Constants.ROLE_TYPE);
    
    if (roleTypeSession == null) {
      roleTypeSession = "";
    }
     
    switch(roleTypeSession.toString()){
      case Constants.ROLE_RESERVATION_STAFF:
        redirectTo("/reservation/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_DELIVERY_STAFF:
        redirectTo("/delivery/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_CUSTOMER:
        redirectTo("/menu?faces-redirect=true");
        break;
    }
  }
  
  public void onlyReservationStaff(){
    Object roleTypeSession = SessionUtil.getAttribute(Constants.ROLE_TYPE);
     
    if (roleTypeSession == null) {
      roleTypeSession = "";
    }
    
    switch(roleTypeSession.toString()){
      case Constants.ROLE_MANAGEMENT_STAFF:
        redirectTo("/management/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_DELIVERY_STAFF:
        redirectTo("/delivery/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_CUSTOMER:
        redirectTo("/menu?faces-redirect=true");
        break;
    }
  }
  
  public void onlyDeliveryStaff(){
    Object roleTypeSession = SessionUtil.getAttribute(Constants.ROLE_TYPE);
    
    if (roleTypeSession == null) {
      roleTypeSession = "";
    }
    
    switch(roleTypeSession.toString()){
      case Constants.ROLE_MANAGEMENT_STAFF:
        redirectTo("/management/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_RESERVATION_STAFF:
        redirectTo("/reservation/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_CUSTOMER:
        redirectTo("/menu?faces-redirect=true");
        break;
    }
  }
  
  public void onlyCustomerStaff(){
    Object roleTypeSession = SessionUtil.getAttribute(Constants.ROLE_TYPE);
         
    if (roleTypeSession == null) {
      roleTypeSession = "";
    }
    
    switch(roleTypeSession.toString()){
      case Constants.ROLE_MANAGEMENT_STAFF:
        redirectTo("/management/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_RESERVATION_STAFF:
        redirectTo("/reservation/dashboard?faces-redirect=true");
        break;
      case Constants.ROLE_DELIVERY_STAFF:
        redirectTo("/delivery/dashboard?faces-redirect=true");
        break;
    }
  }
  
  private void redirectTo(String url){
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    
    try {
      context.redirect(context.getRequestContextPath() + url);
    } catch (IOException e) {
      System.out.println("com.blank.delivery.utils.AuthUtil.OnlyManagementStaff() " + e.getMessage());
    }
  }
}
