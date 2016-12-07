package controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Estimator;
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
public class EstimatorController {

  @Autowired
  TriathlonRepository triRepo;// + setter

  @Autowired
  UserRepository userRepo;// + setter


  @RequestMapping(value = "/estimate", method = RequestMethod.POST)
  public TriathlonTime estimateTriathlon(@RequestParam(value = "name") String name,
      @RequestParam(value = "swimDist") double swimDist,
      @RequestParam(value = "bikeDist") double bikeDist,
      @RequestParam(value = "runDist") double runDist,
      
      @RequestParam(value = "bikeElev") double bikeElev,
      @RequestParam(value = "runElev") double runElev,
      @RequestParam(value = "startTime") String startTime,
      @RequestParam(value = "weather") String weather,
      @RequestParam(value = "temperature") double temperature,
      @RequestParam(value = "username") String username) {

    TriathlonDistance triDist = new TriathlonDistance(swimDist, bikeDist, runDist);
    TriathlonTime triTime = new TriathlonTime();
    TriathlonElevation triElev = new TriathlonElevation(bikeElev, runElev);
    Triathlon newTri = new Triathlon(triDist, triElev, triTime, name, "", new Date(),
        startTime, WeatherConditions.valueOf(weather), temperature);
    
    ArrayList<Triathlon> previousTris = new ArrayList<Triathlon>();
    
    for (User user : userRepo.findByUsername(username)) {
      for (Long id : user.getTris()) {
        previousTris.add(triRepo.findOne(id));
      }
    }
    
    
   Triathlon estimatedTri = Estimator.estimateNewTriathlon(previousTris, newTri);
    
    
    return estimatedTri.getTime();
  }
}
