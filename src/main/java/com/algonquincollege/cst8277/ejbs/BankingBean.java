/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: BankingBean.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 */
package com.algonquincollege.cst8277.ejbs;

import static com.algonquincollege.cst8277.util.MyConstants.PU_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.models.AccountBase;

@Stateless
public class BankingBean {

    @PersistenceContext(unitName = PU_NAME)
    protected EntityManager em;

    //TODO - methods to handle CRUD for Banking entities
    
    public List<AccountBase> getBankAccountsFor(int accountId) {
        TypedQuery<AccountBase> query = em.createQuery("SELECT a FROM Account a", AccountBase.class);
        return query.getResultList();
    }

    @Transactional
    public AccountBase createAccount(AccountBase account) {
        em.persist(account);
        return account;
    }

    public AccountBase readAccount(int id) {
        return em.find(AccountBase.class, id);
    }

    @Transactional
    public AccountBase updateAccount(AccountBase account) {
        return em.merge(account);
    }

    @Transactional
    public void deleteAccount(int id) {
        AccountBase account = readAccount(id);
        if (account != null) {
          em.remove(account);
        }
    }
}