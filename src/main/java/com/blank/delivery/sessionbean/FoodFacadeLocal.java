/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.Food;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface FoodFacadeLocal {

  void create(Food food);

  void edit(Food food);

  void remove(Food food);

  Food find(Object id);

  List<Food> findAll();

  List<Food> findRange(int[] range);

  int count();
  
  List<Food> whereQuantityMoreThanZero();
  
}
