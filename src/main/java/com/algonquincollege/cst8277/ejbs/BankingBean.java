/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: BankingBean.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 * 
 */
package com.algonquincollege.cst8277.ejbs;

import static com.algonquincollege.cst8277.util.MyConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.models.ChequingAccount;
import com.algonquincollege.cst8277.models.AccountBase;
import com.algonquincollege.cst8277.models.User;

@Stateless
public class BankingBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    /**
     *  Creates a chequing account.
     * @param accountId the account to fetch
     * @return account
     */
    public AccountBase createChequingAccounts(int balance, int userId) {
        ChequingAccount c = new ChequingAccount();
        c.setBalance(balance);
        c.addOwner(em.find(User.class, userId));
        em.persist(c);
        return c;
    }

    
    
    /**
     *  Gets account by accountId
     * @param accountId the account to fetch
     * @return account
     */
    public List<AccountBase> getBankAccountsFor(int accountId) {
        TypedQuery<AccountBase> query = em.createQuery("SELECT a FROM AccountBase a WHERE a.id = :accountId",
                AccountBase.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }

    /**
     * 
     * @param account the account to create
     * @return Account
     */
    @Transactional
    public AccountBase createAccount(AccountBase account) {
        em.persist(account);
        return account;
    }

    /**
     * Reads an account
     * @param id the id of account to read
     * @return the account object
     */
    public AccountBase readAccount(int id) {
        return em.find(AccountBase.class, id);
    }

    /**
     *  Updates an account
     * @param account the account to be updated
     * @return updated account
     */
    @Transactional
    public AccountBase updateAccount(AccountBase account) {
        return em.merge(account);
    }

    /**
     * Deletes an account
     * @param id the id of the account to delete
     */
    @Transactional
    public void deleteAccount(int id) {
        AccountBase account = readAccount(id);
        if (account != null) {
          em.remove(account);
        }
    }
    
    /**
     * Gets all the users
     * @return List<User> of all users
     */
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
    
    /**
     * Creates a new User
     * @param username the user's name
     * @return User
     */
    @Transactional
    public User createUser(String username) {
        User usr = new User();
        usr.setName(username);
        em.persist(usr);
        return usr;
    }
}