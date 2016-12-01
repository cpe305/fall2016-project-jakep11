package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Time;
import model.Triathlon;
import model.TriathlonTime;
import model.User;
import repositories.TriathlonRepository;
import repositories.UserRepository;

@RestController
public class TriathlonController {

  @Autowired
  TriathlonRepository triRepo;// + setter
  
  @Autowired
  UserRepository userRepo;// + setter
  
  @RequestMapping("/triathlons")
  public List<Triathlon> getTriathlonsForUser(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<Triathlon>();
    //Only one user
    System.out.println("hi");

    for (User user : userRepo.findByUsername(username)) {
      System.out.println(user);
      System.out.println(user.getTris().size());

      for (Long id : user.getTris()) {
        System.out.println(id);
        tris.add(triRepo.findOne(id));
      }
    }
    return tris;
  }
  
  @RequestMapping("/addTriathlon")
  public void addTriathlon() {
    
  }
  
  
}
