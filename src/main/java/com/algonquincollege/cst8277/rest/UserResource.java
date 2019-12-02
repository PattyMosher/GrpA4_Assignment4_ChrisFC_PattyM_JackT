/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: UserResource.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 * 
 * @author Patty Mosher, Jack Tan, Chris Fortin-Cherryholme
 * @modified Nov 2019
 */

package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.util.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.util.MyConstants.USER_ROLE;

import java.util.List;

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

import com.algonquincollege.cst8277.ejbs.BankingBean;
import com.algonquincollege.cst8277.models.AccountBase;
import com.algonquincollege.cst8277.models.PlatformUser;
import com.algonquincollege.cst8277.models.User;

@Path("user")
public class UserResource {

    @EJB
    protected BankingBean bankingBean;

    @RolesAllowed({ADMIN_ROLE}) // can't let just ANYONE create a new bank account/user
    @POST
    @Path("{name}")
    public Response newBankingUser(@PathParam("name") String username, User user) {
        User newUser = bankingBean.createUser(username, user);
        //probably should create PlatformUser too...
        return Response.ok(newUser).build();
    }

    @Inject
    protected SecurityContext sc;
    
    @RolesAllowed({ADMIN_ROLE, USER_ROLE})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        Response response = null;
        if (sc.isCallerInRole(ADMIN_ROLE)) {
            List<User> users = bankingBean.getAllUsers();
            response = Response.ok(users).build();
        }
        else {
            PlatformUser platformUser = (PlatformUser)sc.getCallerPrincipal();
            User bankingUser = platformUser.getBankingUser();
            if (bankingUser == null) {
                response = Response.noContent().build();
            }
            else {
                response = Response.ok(bankingUser).build();
            }
        }
        return response;
    }
    
//    @RolesAllowed({ADMIN_ROLE, USER_ROLE})
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        Response response = null;
        if (sc.isCallerInRole(ADMIN_ROLE)) {
            User user = bankingBean.readUser(id);
            response = Response.ok(user).build();
        }
        else {
            PlatformUser platformUser = (PlatformUser)sc.getCallerPrincipal();
            User bankingUser = platformUser.getBankingUser();
            if (bankingUser == null) {
                response = Response.noContent().build();
            }
            else {
                response = Response.ok(bankingUser).build();
            }
        }
        return response;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        Response response = null;
//        if (sc.isCallerInRole(ADMIN_ROLE)) {
            bankingBean.deleteUser(id);
            response = Response.ok().build();
//        }
        return response;
    }
}
