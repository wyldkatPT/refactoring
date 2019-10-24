package com.celfocus.training.user;

import junit.framework.TestCase;

import java.util.Date;

public class UserRequesterFrontendTest extends TestCase {

    public void testReturnFrontendUser() {
        User user = new User("name", new Date(), true);
        UserRequestFactory factory = new UserRequestFactory() {};
        UserRequesterFrontend frontend = new UserRequesterFrontend(factory, null);
        assertTrue(frontend.returnFrontendUser("xml", user).contains("<?xml"));
        assertTrue(frontend.returnFrontendUser("html", user).contains("<div>"));
        assertEquals("", frontend.returnFrontendUser("test", user));
    }
}