package com.blank.delivery.models;

import com.blank.delivery.models.EWallet;
import com.blank.delivery.models.Reservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-23T02:20:06")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Reservation> deliveryOrders;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile ListAttribute<User, Reservation> reservations;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, EWallet> eWallet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;

}