package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-11-26T22:34:43.639-0500")
@StaticMetamodel(PlatformRole.class)
public class PlatformRole_ extends ModelBase_ {
	public static volatile SingularAttribute<PlatformRole, Integer> id;
	public static volatile ListAttribute<PlatformRole, PlatformUser> platformUsers;
	public static volatile SingularAttribute<PlatformRole, String> roleName;
}
