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
@Named(value = "eWalletController")
@SessionScoped
public class EWalletController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  @EJB
  private EWalletFacadeLocal eWalletFacade;
  
  private EWallet eWallet;
  private float balance;
  private float amount = 0;
    
  /**
   * Creates a new instance of EWalletController
   */
  public EWalletController() {
  }
  
  @PostConstruct
  public void onInit(){
    User currentCustomer = userFacade.find(SessionUtil.getUserId());
    eWallet = currentCustomer.geteWallet();
    
    if (eWallet != null) {
      balance = eWallet.getBalance();
    }else{
      eWallet = new EWallet(currentCustomer);
      eWallet.setBalance(amount);
      balance = eWallet.getBalance();
      eWalletFacade.create(eWallet);
    }
  }

  @PreDestroy
  public void onDestroy(){
    eWallet = null;
  }

  public EWallet geteWallet() {
    return eWallet;
  }

  public void seteWallet(EWallet eWallet) {
    this.eWallet = eWallet;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }
    
  public void topUp(){
    balance += amount;
    eWallet.setBalance(balance);
    eWalletFacade.edit(eWallet);
    
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "RM" + amount + " has been added to your e-wallet", null));
    
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/e-wallet");
    } catch (IOException e) {
      
    }
  }
}
