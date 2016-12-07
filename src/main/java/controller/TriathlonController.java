package controller;

import model.Time;
import model.Triathlon;
import model.Triathlon.WeatherConditions;
import model.TriathlonDistance;
import model.TriathlonElevation;
import model.TriathlonTime;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import repositories.TriathlonRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


@RestController
public class TriathlonController {

  @Autowired
  TriathlonRepository triRepo;// + setter

  @Autowired
  UserRepository userRepo;// + setter

  /**
   * getTriathlonsForUser gets all triathlons for the logged in user.
   * @param username unique username
   * @return returns all triathlons
   */
  @RequestMapping("/triathlons")
  public List<Triathlon> getTriathlonsForUser(@RequestParam(value = "username") String username) {
    ArrayList<Triathlon> tris = new ArrayList<Triathlon>();
    // Only one user
    System.out.println("hi");

    for (User user : userRepo.findByUsername(username)) {
      for (Long id : user.getTris()) {
        tris.add(triRepo.findOne(id));
      }
    }
    return tris;
  }

  @RequestMapping(value = "/triathlon", method = RequestMethod.DELETE)
  public boolean deleteTriathlon(@RequestParam(value = "id") Long id, @RequestParam(value = "username") String username)
      throws Exception {

    triRepo.delete(id);
    User user = userRepo.findByUsername(username).get(0);
    user.deleteTri(id);
    userRepo.save(user);
    return true;
  }

  /**
   * addTriathlon adds a new triathlon to the database for a specific user.
   * @param name name
   * @param swimDist swimDist
   * @param bikeDist bikeDist
   * @param runDist runDist
   * @param swimTime swimTime
   * @param t1Time t1Time
   * @param bikeTime bikeTime
   * @param t2Time t2Time
   * @param runTime runTime
   * @param bikeElev bikeElev
   * @param runElev runElev
   * @param location location
   * @param date date
   * @param startTime startTime
   * @param weather weather
   * @param temperature temperature
   * @param username username
   * @return returns the added Triathlon
   */
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
    System.out.println(newTri.getId());

    newTri = triRepo.save(newTri);
    User user = userRepo.findByUsername(username).get(0);
    System.out.println(newTri.getId());
    user.addTri(newTri.getId());
    userRepo.save(user);
    return newTri;
  }


}
