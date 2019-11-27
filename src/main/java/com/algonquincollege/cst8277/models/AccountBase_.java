package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-26T23:52:16.359-0500")
@StaticMetamodel(AccountBase.class)
public class AccountBase_ extends ModelBase_ {
	public static volatile SingularAttribute<AccountBase, Integer> id;
	public static volatile SingularAttribute<AccountBase, Double> balance;
	public static volatile ListAttribute<AccountBase, User> owners;
}
