/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: InvestmentAccount.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@DiscriminatorValue(value = "I")
public class InvestmentAccount extends AccountBase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Portfolio portfolio;

	public InvestmentAccount() {
	}

	@OneToOne
	@JoinColumn(name="PORTFOLIO_ID")
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

}