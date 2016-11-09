package controller;

import model.Time;
import model.TriathlonTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;




@RestController
public class HomeController {

  @RequestMapping("/sample")
  public TriathlonTime sample(@RequestParam(value = "name", defaultValue = "World") String name) {
    Time t1 = new Time(3661);
    return new TriathlonTime(t1, t1, t1, t1, t1);
  }
  
  @RequestMapping("/resource")
  public Map<String,Object> home() {
    Map<String,Object> model = new HashMap<String,Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

//  @RequestMapping(path = "/index", method = RequestMethod.GET)
//  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
//  @RequestMapping(path = "/index", method = RequestMethod.GET)
//  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
//  @RequestMapping(path = "/index", method = RequestMethod.GET)
//  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
//  @RequestMapping(path = "/index", method = RequestMethod.GET)
//  public TriathlonTime logout(@RequestParam(value = "name", defaultValue = "World") String name) {
//    Time t1 = new Time(3661);
//    return new TriathlonTime(t1, t1, t1, t1, t1);
//  }
}
