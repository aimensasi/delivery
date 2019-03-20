/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "dashboardController")
@SessionScoped
public class DashboardController implements Serializable {
  
  @EJB
  private UserFacadeLocal userFacade;
  

  /**
   * Creates a new instance of DashboardController
   */
  public DashboardController() {
  }
  
  public String getCurrentUserName(){
    int id = SessionUtil.getUserId();
    
    if (id == -1) {
      return "User Profile";
    }
    User user = userFacade.find(id);
    
    return user.getName();
  }
  
}
