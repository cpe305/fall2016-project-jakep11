package controller;

import model.Stats;
import model.Triathlon;
import model.TriathlonTime;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import repositories.TriathlonRepository;
import repositories.UserRepository;

import java.util.ArrayList;

@RestController
public class StatsController {
  @Autowired
  UserRepository userRepo;
  
  @Autowired
  TriathlonRepository triRepo;
  
  
  @RequestMapping(path = "/averageSwimTime", method = RequestMethod.GET)
  public int averageSwimTime(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageSwimTime(tris).getTimeInSeconds();
  }
  
  @RequestMapping(path = "/averageBikeTime", method = RequestMethod.GET)
  public int averageBikeTime(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageBikeTime(tris).getTimeInSeconds();
  }
  
  @RequestMapping(path = "/averageRunTime", method = RequestMethod.GET)
  public int averageRunTime(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageRunTime(tris).getTimeInSeconds();
  }
  
  @RequestMapping(path = "/averageSwimDistance", method = RequestMethod.GET)
  public double averageSwimDistance(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageSwimDistance(tris);
  }
  
  @RequestMapping(path = "/averageBikeDistance", method = RequestMethod.GET)
  public double averageBikeDistance(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageBikeDistance(tris);
  }
  
  @RequestMapping(path = "/averageRunDistance", method = RequestMethod.GET)
  public double averageRunDistance(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageRunDistance(tris);
  }
  
  @RequestMapping(path = "/averageTimeFromAllTris", method = RequestMethod.GET)
  public TriathlonTime averageTimeFromAllTris(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAverageTimeFromAllTris(tris);
  }
  
  @RequestMapping(path = "/averagePaceFromAllTris", method = RequestMethod.GET)
  public Triathlon averagePaceFromAllTris(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<>();
    User user = userRepo.findByUsername(username).get(0);
    for (Long l : user.getTris()) {
      tris.add(triRepo.findOne(l));
    }
    return Stats.getAveragePaceFromAllTris(tris);
  }
}
