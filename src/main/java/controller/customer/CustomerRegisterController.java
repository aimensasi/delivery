/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;


import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import com.blank.delivery.utils.SessionUtil;
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
@Named(value = "customerRegisterController")
@SessionScoped
public class CustomerRegisterController implements Serializable {

  @EJB
  private UserFacadeLocal userFacade;
  private User user;
  private String name;
  private String email;
  private String password;
  
  private String message;
  
  /**
   * Creates a new instance of RegisterController
   */
  public CustomerRegisterController() { }

  @PostConstruct
  public void onInit(){
    
  }

  @PreDestroy
  public void onDestroy(){

  }
  
  
  public String register(){
    
    if (!userFacade.isUniqueEmail(email)) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Or Password is inncorect", null));    
      return null;
    }
    
    user = new User();
    
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Constants.ROLE_CUSTOMER);

    
    
    try {
      userFacade.create(user);
     
      SessionUtil.setAttribue(Constants.ROLE_TYPE, Constants.ROLE_MANAGEMENT_STAFF);
      SessionUtil.setAttribue(Constants.USER_ID, user.getId());
      
      return "/customer/dashboard?faces-redirect=true";
      
    } catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something Went Wrong, Please Try Again", null));    
    }

    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
