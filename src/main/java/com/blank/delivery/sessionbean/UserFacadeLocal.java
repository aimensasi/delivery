/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface UserFacadeLocal {

  void create(User user);

  void edit(User user);

  void remove(User user);

  User find(Object id);

  List<User> findAll();

  List<User> findRange(int[] range);

  int count();
  
  Boolean isUniqueEmail(String email);
  
  Boolean isUniqueIC(String IC);
  
  Boolean ManagementStaffExists();
  
  User attempt(String email, String password);
  
  List<User> findByRole(String role);
  
  List<User> findByRole(String role, int currentUserId);
  
}
