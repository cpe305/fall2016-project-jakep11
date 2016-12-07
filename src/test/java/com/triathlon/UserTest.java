package com.triathlon;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;

import controller.TriathlonController;
import junit.framework.Assert;
import model.Time;
import model.Triathlon;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.User;
import model.Triathlon.WeatherConditions;

public class UserTest {

  User user1, user2, user3;
  @Before
  public void setup() {

    user1 = new User("Jake", "Pickett");
    user2 = new User("admin", "password");
    user3 = new User("Joe", BCrypt.hashpw("Smith", BCrypt.gensalt()));
    
  }
  
  
  @Test
  public void usernameTest() {
    user1.setUsername("Jacob");
    assertEquals("Jacob", user1.getUsername());
  }
  
  @Test
  public void passwordTest() {
    assertEquals("password", user2.getPassword());
  }
  
  @Test
  public void triTest() {
    user3.addTri(1L);
    user3.addTri(2L);
    user3.addTri(4L);
    user3.deleteTri(2L);
    user3.deleteTri(4L);
    
    assertEquals(1, (long)user3.getTris().get(0));
  }
  
  @Test
  public void miscTest() {
    user3.getId();
    user3.getRoles();
    user3.toString();
    //assertEquals("password", user2.getPassword());
  }
  
}
