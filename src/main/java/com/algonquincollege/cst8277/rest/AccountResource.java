package com.algonquincollege.cst8277.rest;

import java.security.Principal;
import java.util.List;

import static com.algonquincollege.cst8277.util.MyConstants.USER_ROLE;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.algonquincollege.cst8277.ejbs.BankingBean;
import com.algonquincollege.cst8277.models.AccountBase;
import com.algonquincollege.cst8277.models.PlatformUser;

@Path("account")
public class AccountResource {
    
    @EJB
    protected BankingBean bean;
    
    @Inject
    protected SecurityContext sc;
    
    /**
     * Security omitted for now while testing. See next method for an example.
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccounts(@PathParam("id") int id) {
        List<AccountBase> accounts = bean.getBankAccountsFor(id);
        return Response.ok(accounts).build();
    }
    
    /**
     * Mike Norman example.
     * @param id
     * @return
     */
    @GET
    @RolesAllowed(USER_ROLE)
    @Path("{id}")
    public Response getUserById(@PathParam("id") int id) {
        Response response = null;
        Principal principal = sc.getCallerPrincipal();
        if (principal == null) {
            response = Response.serverError().entity("{\"message\":\"missing principal\"}").build();
        }
        else {
            PlatformUser platformUser = (PlatformUser)principal;
            if (platformUser.getBankingUser() == null || platformUser.getBankingUser().getId() != id) {
                response = Response.status(Status.UNAUTHORIZED).entity("{\"message\":\"cannot get user\"}").build();
            }else {
                response = Response.ok(platformUser.getBankingUser()).build();
            }
        }
        return response;

   }
}
