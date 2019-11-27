/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: User.java
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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The persistent class for the USER_ACCOUNT database table.
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 */
@Entity(name = "User")
@Table(name="USER_ACCOUNT")
public class User extends ModelBase implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected List<AccountBase> accounts = new ArrayList<>();

    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_ID")
    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(targetEntity = AccountBase.class)
    @JoinTable(name="USER_ACCOUNT_BANKACCOUNT",
    joinColumns=@JoinColumn(name="ACCOUNT_ID", referencedColumnName="ACCOUNT_ID"))
    public List<AccountBase> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<AccountBase> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(AccountBase a) {
        getAccounts().add(a);
    }
}