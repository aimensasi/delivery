/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Reservation;
import com.blank.delivery.models.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface ReservationFacadeLocal {

  void create(Reservation reservation);

  void edit(Reservation reservation);

  void remove(Reservation reservation);

  Reservation find(Object id);

  List<Reservation> findAll();

  List<Reservation> findRange(int[] range);

  int count();
  
  List<Reservation> getCurrentCustomerOrders();
  
  List<Reservation> findApprovedOrdersWithDeliverStaff(User deliveryStaff);
  
  List<Reservation> findDeliveredOrdersWithDeliverStaff(User deliveryStaff);
  
  List<Reservation> findByDeliveryStaffId(User deliveryStaff);
}
