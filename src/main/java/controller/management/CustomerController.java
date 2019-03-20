/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "mCustomerController")
@SessionScoped
public class CustomerController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  
  private User selectedCustomer;
  private List<User> customers;
  
  
  private String name;
  private String email;
  

  /**
   * Creates a new instance of CustomerController
   */
  public CustomerController() {
  }
  
  @PostConstruct
  public void onInit(){
    customers = userFacade.findByRole(Constants.ROLE_CUSTOMER);
  }

  @PreDestroy
  public void onDestroy(){
    customers = null;
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

  public User getSelectedCustomer() {
    return selectedCustomer;
  }

  public void setSelectedCustomer(User selectedCustomer) {
    this.selectedCustomer = selectedCustomer;
  }

  public List<User> getCustomers() {
    return customers;
  }

  public void setCustomers(List<User> customers) {
    this.customers = customers;
  }
  
  
  public void updateCustomer() {
    userFacade.edit(selectedCustomer);
    refreshPage();
  }

  public void deleteCustomer(User customer) {
    customers.remove(customer);
    userFacade.remove(customer);
    
    refreshPage();
  }
  
  private void refreshPage(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/management/customers");
    } catch (IOException e) {
      
    }
  }
}
