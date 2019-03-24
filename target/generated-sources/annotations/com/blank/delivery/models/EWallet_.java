package com.blank.delivery.models;

import com.blank.delivery.models.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-24T08:54:56")
@StaticMetamodel(EWallet.class)
public class EWallet_ { 

    public static volatile SingularAttribute<EWallet, Float> balance;
    public static volatile SingularAttribute<EWallet, Integer> id;
    public static volatile SingularAttribute<EWallet, User> customer;

}