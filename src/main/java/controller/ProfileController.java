/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "profileController")
@SessionScoped
public class ProfileController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  private User currentUser;
  private String currentPassword;
  private String password;
  private String confirmPassword;

  /**
   * Creates a new instance of ProfileController
   */
  public ProfileController() {
  }
  
  @PostConstruct
  public void onInit(){
    int id = SessionUtil.getUserId();    
    currentUser = userFacade.find(id);
  }

  @PreDestroy
  public void onDestroy(){
    currentPassword = null;
    password = null;
    confirmPassword = null;
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User currentUser) {
    this.currentUser = currentUser;
  }

  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  
  public void update(){
    userFacade.edit(currentUser);
    
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Updated", null));
//    refreshPage();
  }
  
  public void changePassword(){
    
    
    if (!currentUser.getPassword().equals(currentPassword)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password is inncorect", null));
      return;
    }
    
    if (!password.equals(confirmPassword)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "New password and confirm password don't match", null));
      return;
    }
    
    currentUser.setPassword(password);
    userFacade.edit(currentUser);
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Changed Successfully", null));
    
    currentPassword = null;
    password = null;
    confirmPassword = null;
  }
  
  private void refreshPage(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/management/profile");
    } catch (IOException e) {
      
    }
  }
  
}
