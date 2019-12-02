/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: AccountResource.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 */

package com.algonquincollege.cst8277.rest;

import java.security.Principal;
import java.util.List;

import static com.algonquincollege.cst8277.util.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.util.MyConstants.USER_ROLE;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.algonquincollege.cst8277.ejbs.BankingBean;
import com.algonquincollege.cst8277.models.AccountBase;
import com.algonquincollege.cst8277.models.ChequingAccount;
import com.algonquincollege.cst8277.models.PlatformUser;
import com.algonquincollege.cst8277.models.User;
@Path("account")
public class AccountResource {
    
    @EJB
    protected BankingBean bean;
    
    @Inject
    protected SecurityContext sc;
    
    /**
     * Create an account.
     * @param id
     * @param newAccount
     * @return
     */
    @RolesAllowed({ADMIN_ROLE}) // can't let just ANYONE create a new bank account/user
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newAccount(@PathParam("id") int id, AccountBase newAccount) {
        ChequingAccount chequingAccount = (ChequingAccount) bean.createChequingAccounts(newAccount);
        return Response.ok(chequingAccount).build();
    }

    
    /**
     * Security omitted for now while testing. See next method for an example.
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccounts(@PathParam("id") int accountId) {
        List<AccountBase> accounts = bean.getBankAccountsFor(accountId);
        return Response.ok(accounts).build();
    }
    
    /**
     * Security omitted for now while testing. See next method for an example.
     * @param id
     * @return
     */
//    @RolesAllowed({ADMIN_ROLE, USER_ROLE})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccounts() {
        Response response = null;
//        if (sc.isCallerInRole(ADMIN_ROLE)) {
            List<AccountBase> accounts = bean.getAllAccounts();
            Response.ok(accounts).build();
//        }else {
//            response = Response.status(Status.UNAUTHORIZED).entity("{\"message\":\"cannot get user\"}").build();
//        }
        return response;
    }
    
    /**
     * Delete an account based on id
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("id") int id) {
        Response response = null;
//        if (sc.isCallerInRole(ADMIN_ROLE)) {
            bean.deleteAccount(id);
            response = Response.ok().build();
//        }
        return response;
    }
}
