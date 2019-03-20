/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import com.blank.delivery.utils.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "deliveryStaffController")
@SessionScoped
public class DeliveryStaffController implements Serializable {

  @EJB
  private UserFacadeLocal staffFacade;
  
  private User selectedStaff;
  private User newStaff;
  private List<User> staffList;
  
  
  /**
   * Creates a new instance of DeliveryStaffController
   */
  public DeliveryStaffController() {
  }
  
  @PostConstruct
  public void onInit(){
    newStaff = new User();
    staffList = staffFacade.findByRole(Constants.ROLE_DELIVERY_STAFF, SessionUtil.getUserId());
  }

  @PreDestroy
  public void onDestroy(){
    staffList = null;
    newStaff = null;
    selectedStaff = null;
  }
  
   public User getSelectedStaff() {
    return selectedStaff;
  }

  public void setSelectedStaff(User selectedStaff) {
    this.selectedStaff = selectedStaff;
  }

  public User getNewStaff() {
    return newStaff;
  }

  public void setNewStaff(User newStaff) {
    this.newStaff = newStaff;
  }

  public List<User> getStaffList() {
    return staffList;
  }

  public void setStaffList(List<User> staffList) {
    this.staffList = staffList;
  }
  
  
  public void create(){
    if (newStaff != null) {
      newStaff.setPassword(Constants.DEFAULT_PASSWORD);
      newStaff.setRole(Constants.ROLE_DELIVERY_STAFF);
      
      staffFacade.create(newStaff);
      staffList.add(newStaff);
      newStaff = new User();
      refreshPage();
    }
  }
  
  public void update(){
    staffFacade.edit(selectedStaff);
    refreshPage();
  }
  
  public void delete(User staff){
    staffList.remove(staff);
    staffFacade.remove(staff);
    
    refreshPage();
  }
  
  private void refreshPage(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/management/delivery-staff");
    } catch (IOException e) {
      
    }
  }
  
}
