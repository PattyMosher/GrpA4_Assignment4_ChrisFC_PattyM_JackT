package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-27T21:51:01.285-0500")
@StaticMetamodel(Portfolio.class)
public class Portfolio_ extends ModelBase_ {
	public static volatile SingularAttribute<Portfolio, Integer> id;
	public static volatile ListAttribute<Portfolio, Asset> assets;
	public static volatile SingularAttribute<Portfolio, Double> balance;
}
