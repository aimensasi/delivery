package com.blank.delivery.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-24T05:52:38")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Integer> receivedBy;
    public static volatile SingularAttribute<Feedback, String> feedback;
    public static volatile SingularAttribute<Feedback, String> createdBy;
    public static volatile SingularAttribute<Feedback, Integer> orderId;
    public static volatile SingularAttribute<Feedback, Integer> rating;
    public static volatile SingularAttribute<Feedback, Integer> givenBy;
    public static volatile SingularAttribute<Feedback, Integer> id;

}