/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.management;

import com.blank.delivery.models.Food;
import com.blank.delivery.sessionbean.FoodFacadeLocal;
import com.blank.delivery.utils.Constants;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author aimen.s.a.sasi
 */
@Named(value = "mFoodController")
@SessionScoped
public class FoodController implements Serializable{
  
  @EJB
  private FoodFacadeLocal foodFacade;
  
  private Food selectedFood;
  private Food newFood;
  private List<Food> foods;
  
  

  /**
   * Creates a new instance of FoodController
   */
  public FoodController() {
  }
  
  @PostConstruct
  public void onInit(){
    foods = foodFacade.findAll();
    newFood = new Food();
  }

  @PreDestroy
  public void onDestroy(){
    foods = null;
    newFood = null;
    selectedFood = null;
  }

  public Food getSelectedFood() {
    return selectedFood;
  }

  public void setSelectedFood(Food selectedFood) {
    this.selectedFood = selectedFood;
  }

  public Food getNewFood() {
    return newFood;
  }

  public void setNewFood(Food newFood) {
    this.newFood = newFood;
  }

  public List<Food> getFoods() {
    return foods;
  }

  public void setFoods(List<Food> foods) {
    this.foods = foods;
  }

  
  public void create(){
    if (newFood != null) {
      foodFacade.create(newFood);
      foods.add(newFood);
      newFood = new Food();
      refreshPage();
    }
  }
  
  public void delete(Food food){
    foods.remove(food);
    foodFacade.remove(food);
    
    refreshPage();
  }
  
  public void update(){
    foodFacade.edit(selectedFood);
    refreshPage();
  }
  
  private void refreshPage(){
    try {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
      context.redirect(context.getRequestContextPath()+ "/management/food");
    } catch (IOException e) {
      
    }
  }
}
