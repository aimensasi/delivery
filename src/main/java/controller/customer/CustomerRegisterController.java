/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;


import com.blank.delivery.models.EWallet;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.EWalletFacadeLocal;
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
  @EJB
  private EWalletFacadeLocal eWalletFacade;
  
  
  private User user;
  
  /**
   * Creates a new instance of RegisterController
   */
  public CustomerRegisterController() { }

  @PostConstruct
  public void onInit(){
    user = new User();
    user.setRole(Constants.ROLE_CUSTOMER);
    user.setIsVerified(true);
  }

  @PreDestroy
  public void onDestroy(){

  }
  
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
  public String register(){
    
    if (!userFacade.isUniqueEmail(user.getEmail()) || !userFacade.isUniqueIC(user.getIc())) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Or IC has already been taken", null));    
      return null;
    }
    

    try {
      userFacade.create(user);
      
      EWallet eWallet = new EWallet(user);
      eWalletFacade.create(eWallet);
     
      SessionUtil.setAttribue(Constants.ROLE_TYPE, user.getRole());
      SessionUtil.setAttribue(Constants.USER_ID, user.getId());
      
      return "/menu?faces-redirect=true";
      
    } catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something Went Wrong, Please Try Again", null));    
    }

    return null;
  }
}
