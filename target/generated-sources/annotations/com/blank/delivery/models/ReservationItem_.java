package com.blank.delivery.models;

import com.blank.delivery.models.Food;
import com.blank.delivery.models.Reservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-24T12:55:34")
@StaticMetamodel(ReservationItem.class)
public class ReservationItem_ { 

    public static volatile SingularAttribute<ReservationItem, Integer> quantity;
    public static volatile SingularAttribute<ReservationItem, Reservation> reservation;
    public static volatile SingularAttribute<ReservationItem, Integer> id;
    public static volatile SingularAttribute<ReservationItem, Food> food;

}