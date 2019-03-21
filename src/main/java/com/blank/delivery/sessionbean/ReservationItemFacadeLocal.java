/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.ReservationItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface ReservationItemFacadeLocal {

  void create(ReservationItem reservationItem);

  void edit(ReservationItem reservationItem);

  void remove(ReservationItem reservationItem);

  ReservationItem find(Object id);

  List<ReservationItem> findAll();

  List<ReservationItem> findRange(int[] range);

  int count();
  
}
