package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-27T21:51:01.314-0500")
@StaticMetamodel(User.class)
public class User_ extends ModelBase_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile ListAttribute<User, AccountBase> accounts;
	public static volatile SingularAttribute<User, String> name;
}
