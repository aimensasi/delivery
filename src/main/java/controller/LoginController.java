/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import com.blank.delivery.utils.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  
  private String email;
  private String password;

  /**
   * Creates a new instance of LoginController
   */
  public LoginController() {
  }
  
  @PostConstruct
  public void onInit(){
    
  }

  @PreDestroy
  public void onDestroy(){

  }
  
  
  public String login(){
    User user = userFacade.attempt(email, password);
    
    if (user == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Or Password is inncorect", null));
      return null;
    }
    
    SessionUtil.setAttribue(Constants.ROLE_TYPE, user.getRole());
    SessionUtil.setAttribue(Constants.USER_ID, user.getId());
    
    String redirectUrl = "";
    
    switch(user.getRole()){
      case Constants.ROLE_MANAGEMENT_STAFF:
        redirectUrl = "/management/dashboard?faces-redirect=true";
        break;
      case Constants.ROLE_RESERVATION_STAFF:
        break;
      case Constants.ROLE_DELIVERY_STAFF:
        break;
      case Constants.ROLE_CUSTOMER:
        break;
      default:
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something Went Wrong", null));
        break;
    }
    
    
    return redirectUrl;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
}
