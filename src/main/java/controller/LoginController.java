package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import app.*;
import model.User;
import repositories.UserRepository;

import java.security.Principal;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;



@RestController
public class LoginController {
  @Autowired
  private UserService service;
  // @RequestMapping(path = "/login", method = RequestMethod.POST)
  // public TriathlonTime login(@RequestParam(value = "name", defaultValue = "World") String name) {
  // Time t1 = new Time(3661);
  // return new TriathlonTime(t1, t1, t1, t1, t1);
  // }
  //
  // @RequestMapping(path = "/logout", method = RequestMethod.POST)
  // public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name)
  // {
  // Time t1 = new Time(3661);
  // return new TriathlonTime(t1, t1, t1, t1, t1);
  // }

  // @Autowired
  // UserRepository myRepo;// + setter
  //
  // @RequestMapping("/users")
  // public User user() {
  // ArrayList<User> users = new ArrayList<User>();
  // for (User u : myRepo.findAll()) {
  // users.add(u);
  // }
  //
  //
  // return myRepo.findOne(1L);
  // }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public void registerUserAccount(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password, WebRequest request) {

    // User registered = new User(username, password);
    User registered = createUserAccount(username, password);

  }

  private User createUserAccount(String username, String password) {
    
    User registered = null;
    registered = service.registerNewUserAccount(username, password);

    return registered;
  }


  @RequestMapping("/user")
  public String user(String user) {
    System.out.println("logging in?");
    return "hello";
  }

}
