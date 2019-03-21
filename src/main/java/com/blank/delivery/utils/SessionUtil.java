/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.utils;

import com.blank.delivery.models.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aimen.s.a.sasi
 */
public class SessionUtil {
  
  public SessionUtil(){
    
  }
  
  
  public static HttpSession getSession() {
    return (HttpSession)
      FacesContext.
      getCurrentInstance().
      getExternalContext().
      getSession(false);
  }
  
  public static HttpServletRequest getRequest() {
    return (HttpServletRequest) FacesContext.
       getCurrentInstance().
       getExternalContext().getRequest();
  }
  
  public static void setAttribue(String key, Object Value){
    getSession().setAttribute(key, Value);
  }
  
  public static Object getAttribute(String key){
    HttpSession session = getSession();
   
    if (session == null) {
      return null;
    }
    return getSession().getAttribute(key);
  }
  
  /**
  * Get email from session
  * @return
  */
  public static String getUserEmail(){
    HttpSession session = getSession();
    if ( session != null )
     return (String) session.getAttribute("email");
    else
     return null;
  }

 /**
  * Get userid from session
  * @return
  */
 public static int getUserId(){
    HttpSession session = getSession();
    if ( session != null )
     return (int) session.getAttribute("user_id");
    else
     return -1;
  }
 
}
