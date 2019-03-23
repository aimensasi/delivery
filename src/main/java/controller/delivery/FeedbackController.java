/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.delivery;

import com.blank.delivery.models.Feedback;
import com.blank.delivery.models.User;
import com.blank.delivery.sessionbean.FeedbackFacadeLocal;
import com.blank.delivery.sessionbean.UserFacadeLocal;
import com.blank.delivery.utils.Constants;
import com.blank.delivery.utils.SessionUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "dDeedbackController")
@RequestScoped
public class FeedbackController {
  
  @EJB private UserFacadeLocal userFacade;
  
  @EJB
  private FeedbackFacadeLocal feedbackFacade;
  
  private User deliveryStaff;
  private List<Feedback> feedbackList;
  

  /**
   * Creates a new instance of FeedbackController
   */
  public FeedbackController() {
  }
  
  
  @PostConstruct
  public void onInit(){
    int id = SessionUtil.getUserId();
    
    deliveryStaff = userFacade.find(id);
    feedbackList = feedbackFacade.findByReceivedBy(deliveryStaff);
  }

  @PreDestroy
  public void onDestroy(){
  }

  public List<Feedback> getFeedbackList() {
    return feedbackList;
  }

  public void setFeedbackList(List<Feedback> feedbackList) {
    this.feedbackList = feedbackList;
  }

  
}
