package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-27T21:51:01.169-0500")
@StaticMetamodel(AccountBase.class)
public class AccountBase_ extends ModelBase_ {
	public static volatile SingularAttribute<AccountBase, Integer> id;
	public static volatile ListAttribute<AccountBase, User> owners;
	public static volatile SingularAttribute<AccountBase, Double> balance;
}
