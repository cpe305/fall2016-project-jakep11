package controller;

import model.Time;
import model.TriathlonTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

  @RequestMapping(path = "/login", method = RequestMethod.POST)
  public TriathlonTime login(@RequestParam(value = "name", defaultValue = "World") String name) {
    Time t1 = new Time(3661);
    return new TriathlonTime(t1, t1, t1, t1, t1);
  }
  
  @RequestMapping(path = "/logout", method = RequestMethod.POST)
  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
    Time t1 = new Time(3661);
    return new TriathlonTime(t1, t1, t1, t1, t1);
  }
}
