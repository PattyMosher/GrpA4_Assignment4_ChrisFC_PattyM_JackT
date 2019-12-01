/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: AccountBase.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 */
package com.algonquincollege.cst8277.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
      @JsonSubTypes({
        @Type(value = ChequingAccount.class, name = "C"),
        @Type(value = InvestmentAccount.class, name = "I") ,
        @Type(value = SavingsAccount.class, name = "S")
    })
@Entity(name = "AccountBase")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ACCOUNT")
@DiscriminatorColumn(name = "ACCOUNT_TYPE", length = 1)
public abstract class AccountBase extends ModelBase {
    
    // I moved balance from ModelBase to here
    protected double balance;

    protected List<User> owners;
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    public int getId() {
        return this.id;
    }

    @ManyToMany
    @JoinTable(name = "USER_ACCOUNT_ACCOUNT", 
        joinColumns = @JoinColumn(name =  "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    @JoinColumn(name="ACCOUNT_ID")
    public List<User> getOwners() {
        return owners;
    }
    public void setOwners(List<User> owners) {
        this.owners = owners;
    }
    public void addOwner(User owner) {
        getOwners().add(owner);
    }
}