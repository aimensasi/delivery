package com.blank.delivery.models;

import com.blank.delivery.models.ReservationItem;
import com.blank.delivery.models.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-24T12:55:34")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Float> totalPrice;
    public static volatile SingularAttribute<Reservation, User> deliveryStaff;
    public static volatile SingularAttribute<Reservation, Integer> deliverStaffId;
    public static volatile SingularAttribute<Reservation, Integer> id;
    public static volatile ListAttribute<Reservation, ReservationItem> reservationItemList;
    public static volatile SingularAttribute<Reservation, User> customer;
    public static volatile SingularAttribute<Reservation, String> status;

}