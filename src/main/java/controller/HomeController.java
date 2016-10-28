package controller;

import model.Time;
import model.TriathlonTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

  @RequestMapping("/sample")
  public TriathlonTime sample(@RequestParam(value = "name", defaultValue = "World") String name) {
    Time t1 = new Time(3661);
    return new TriathlonTime(t1, t1, t1, t1, t1);
  }
}
