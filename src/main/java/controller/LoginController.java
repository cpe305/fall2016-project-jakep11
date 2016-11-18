package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import repositories.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;




@RestController
public class LoginController {

//  @RequestMapping(path = "/login", method = RequestMethod.POST)
//  public TriathlonTime login(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
//  
//  @RequestMapping(path = "/logout", method = RequestMethod.POST)
//  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
  
//  @Autowired
//  UserRepository myRepo;// + setter
//  
//  @RequestMapping("/users")
//  public User user() {
//    ArrayList<User> users = new ArrayList<User>();
//    for (User u : myRepo.findAll()) {
//      users.add(u);
//    }
//    
//    
//    return myRepo.findOne(1L);
//  }

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }
  
}
