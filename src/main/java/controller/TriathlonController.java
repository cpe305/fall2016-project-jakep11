package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.User;
import model.Triathlon.WeatherConditions;
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
    // Only one user
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

  // this.name = name;
  // this.swimDist = distance.swim;
  // this.bikeDist = distance.bike;
  // this.runDist = distance.run;
  // this.swimTime = time.swim;
  // this.bikeTime = time.bike;
  // this.runTime = time.run;
  // this.bikeElev = bikeElev;
  // this.runElev = runElev;
  // this.location = location;
  // this.date = date;
  // this.startTime = startTime;
  // this.temperature = temperature;
  // this.username = username;

  @RequestMapping("/addTri")
  public Triathlon addTriathlon(@RequestParam(value = "name") String name,
      @RequestParam(value = "swimDist") double swimDist,
      @RequestParam(value = "bikeDist") double bikeDist,
      @RequestParam(value = "runDist") double runDist,
      @RequestParam(value = "swimTime") int swimTime, 
      @RequestParam(value = "t1Time") int t1Time,
      @RequestParam(value = "bikeTime") int bikeTime, 
      @RequestParam(value = "t2Time") int t2Time,
      @RequestParam(value = "runTime") int runTime,
      @RequestParam(value = "bikeElev") double bikeElev,
      @RequestParam(value = "runElev") double runElev,
      @RequestParam(value = "location") String location, @RequestParam(value = "date") String date,
      @RequestParam(value = "startTime") String startTime,
      @RequestParam(value = "weather") String weather,
      @RequestParam(value = "temperature") double temperature,
      @RequestParam(value = "username") String username) {

    System.out.println("Trying to add new Tri");
    TriathlonDistance triDist = new TriathlonDistance(swimDist, bikeDist, runDist);
    TriathlonTime triTime = new TriathlonTime(new Time(swimTime), new Time(t1Time),
        new Time(bikeTime), new Time(t2Time), new Time(runTime));
    TriathlonElevation triElev = new TriathlonElevation(bikeElev, runElev);
    Triathlon newTri = new Triathlon(triDist, triElev, triTime, name, location, new Date(),
        startTime, WeatherConditions.valueOf(weather), temperature);
    System.out.println(username);
    System.out.println(bikeElev);
    System.out.println(newTri.getID());

    newTri = triRepo.save(newTri);
    User user = userRepo.findByUsername(username).get(0);
    System.out.println(newTri.getID());
    user.addTri(newTri.getID());
    userRepo.save(user);
    return newTri;
  }


}
