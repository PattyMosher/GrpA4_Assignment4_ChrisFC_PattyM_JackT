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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Test;

public class RestClientTestSuite  {

    // TODO - create 10 test-cases that send GET/PUT/POST/DELETE messages
    // to REST'ful endpoints for the Banking entities using the JAX-RS
    // ClientBuilder APIs
    
    WebTarget webTarget;
    
    @Before
    public void setUp() throws Exception {
        Client client = ClientBuilder.newClient();
        UriBuilder uriBuilder = UriBuilder.fromUri(APPLICATION_NAME + APPLICATION_API_VERSION)
                .scheme("http")
                .host("localhost")
                .port(8080);
        webTarget = client.target(uriBuilder);
    }
    
    @Test
    public void testBasicAuth() {
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
    
    @Test
    public void test_hello_post() {
        Response response = webTarget
                .path("account/1")
                .request(APPLICATION_JSON)
                .get();
        assertThat(response.getStatus(), is(200));
    }
    
//    @Test
//    public void test_hello_get() {
//        Response response = webTarget
//                .path("hello")
//                .request(APPLICATION_JSON)
//                .get();
//        assertThat(response.getStatus(), is(200));
//        MessageHolder msgH = response.readEntity(MessageHolder.class);
//        assertEquals("hello from the other side!", msgH.getMsg());
//    }

}