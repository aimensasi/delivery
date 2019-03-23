/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Feedback;
import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface FeedbackFacadeLocal {

  void create(Feedback feedback);

  void edit(Feedback feedback);

  void remove(Feedback feedback);

  Feedback find(Object id);

  List<Feedback> findAll();

  List<Feedback> findRange(int[] range);

  int count();
  
  Feedback getOrCreate(User user, Reservation reservation);
  
}
