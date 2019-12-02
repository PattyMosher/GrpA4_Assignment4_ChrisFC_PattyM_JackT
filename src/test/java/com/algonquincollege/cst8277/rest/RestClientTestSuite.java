/**************************************************************G*********o****o****g**o****og**joob*********************
 * File: RestClientTestSuite.java
 * Course materials (19F) CST 8277
 * @author Mike Norman
 *
 * @date 2019 10
 */
package com.algonquincollege.cst8277.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static com.algonquincollege.cst8277.util.MyConstants.APPLICATION_NAME;
import static com.algonquincollege.cst8277.util.MyConstants.APPLICATION_API_VERSION;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Test;

import com.algonquincollege.cst8277.models.AccountBase;
import com.algonquincollege.cst8277.models.ChequingAccount;
import com.algonquincollege.cst8277.models.User;

public class RestClientTestSuite  {

    // TODO - create 10 test-cases that send GET/PUT/POST/DELETE messages
    // to REST'ful endpoints for the Banking entities using the JAX-RS
    // ClientBuilder APIs
    
    WebTarget webTarget;
    
    /**
     * Setup webTarget.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Client client = ClientBuilder.newClient();
        UriBuilder uriBuilder = UriBuilder.fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
                .scheme("http")
                .host("localhost")
                .port(8080);
        webTarget = client.target(uriBuilder);
    }
    
    /**
     * Test a successful authentication for User data.
     */
    @Test
    public void testBasicAuthUserSuccess() {
        HttpAuthenticationFeature feature = 
            HttpAuthenticationFeature.basic("admin", "admin");
        Client client = ClientBuilder.newClient();
        URI uri = UriBuilder
            .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path("user");
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Test a failed authentication for User data.
     */
    @Test
    public void testBasicAuthUserFail() {
        HttpAuthenticationFeature feature = 
            HttpAuthenticationFeature.basic("NOTadmin", "admin");
        Client client = ClientBuilder.newClient();
        URI uri = UriBuilder
            .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path("user");
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(401));
    }
    
    /**
     * Test a successful authentication for Account data.
     */
    @Test
    public void testBasicAuthAccountSuccess() {
        HttpAuthenticationFeature feature = 
            HttpAuthenticationFeature.basic("admin", "admin");
        Client client = ClientBuilder.newClient();
        URI uri = UriBuilder
            .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path("account");
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Test a failed authentication for Account data.
     */
    @Test
    public void testBasicAuthAccountFail() {
        HttpAuthenticationFeature feature = 
            HttpAuthenticationFeature.basic("NOTadmin", "admin");
        Client client = ClientBuilder.newClient();
        URI uri = UriBuilder
            .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path("account");
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(401));
    }
    
    /**
     * Test a GET request to get all Accounts in database.
     */
    @Test
    public void testAllAccountsGET() {
        HttpAuthenticationFeature feature = 
                HttpAuthenticationFeature.basic("admin", "admin");
            Client client = ClientBuilder.newClient();
            URI uri = UriBuilder
                .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
                .scheme("http")
                .host("localhost")
                .port(8080)
                .build();
            WebTarget webTarget = client
                .register(feature)
                .target(uri)
                .path("account");
            Response response = webTarget
                .request(APPLICATION_JSON)
                .get();
            assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Test a GET request to get an Account in database based on ACCOUNT_ID
     */
    @Test
    public void testAccountGET() {
        Response response = webTarget
                .path("account/1")
                .request(APPLICATION_JSON)
                .get();
        ChequingAccount c = response.readEntity(ChequingAccount.class);
        assertThat(response.getStatus(), is(200));
        assertEquals(1000.00, c.getBalance(),0.01);
    }
    
    /**
     * Test a DELETE request to delete an Account from the database based on ACCOUNT_ID
     */
    @Test
    public void testAccountDelete() {
        Response response = webTarget
                .path("account/1")
                .request(APPLICATION_JSON)
                .delete();
        assertThat(response.getStatus(), anyOf(is(200), is(202), is(204)));
    }
    
    /**
     * Test a GET request to get all Users in database.
     */
    @Test
    public void testAllUsersGET() {
        HttpAuthenticationFeature feature = 
                HttpAuthenticationFeature.basic("admin", "admin");
            Client client = ClientBuilder.newClient();
            URI uri = UriBuilder
                .fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
                .scheme("http")
                .host("localhost")
                .port(8080)
                .build();
            WebTarget webTarget = client
                .register(feature)
                .target(uri)
                .path("user");
            Response response = webTarget
                .request(APPLICATION_JSON)
                .get();
            assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Test a GET request to get an User in database based on USER_ACCOUNT_ID
     */
    @Test
    public void testUserGET() {
        Response response = webTarget
                .path("user/1")
                .request(APPLICATION_JSON)
                .get();
        User u = response.readEntity(User.class);
        assertThat(response.getStatus(), is(200));
        assertEquals("One", u.getName());
    }
    
    /**
     * Test a DELETE request to delete an Account from the database based on USER_ACCOUNT_ID.
     */
    @Test
    public void testUserDelete() {
        Response response = webTarget
                .path("user/1")
                .request(APPLICATION_JSON)
                .delete();
        assertThat(response.getStatus(), anyOf(is(200), is(202), is(204)));
    }


}