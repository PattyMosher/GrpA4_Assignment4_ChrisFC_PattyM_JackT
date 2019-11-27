/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: PlatformUser.java
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
import java.security.Principal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User class used for (JSR-375) Java EE Security authorization/authentication
 */

@Entity
@Table(name="PLATFORM_USER")
public class PlatformUser extends ModelBase implements Principal, Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String username;
    protected String pwHash;
    protected Set<PlatformRole> platformRoles;
    protected User bankingUser;

    public PlatformUser() {
        super();
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwHash() {
        return pwHash;
    }
    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    @ManyToMany(targetEntity = PlatformRole.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="PLATFORM_USER_PLATFORM_ROLE",
      joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
    public Set<PlatformRole> getPlatformRoles() {
        return platformRoles;
    }
    public void setPlatformRoles(Set<PlatformRole> platformRoles) {
        this.platformRoles = platformRoles;
    }
    public void addPlatformRole(PlatformRole role) {
        getPlatformRoles().add(role);
    }
    
    @OneToOne
    @JoinColumn(name = "BANKING_USER_ID", nullable = true)
    public User getBankingUser() {
        return bankingUser;
    }
    public void setBankingUser(User bankingUser) {
        this.bankingUser = bankingUser;
    }

    @Override
    public String getName() {
        return getUsername();
    }

}