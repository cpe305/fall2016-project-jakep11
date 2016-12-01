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
import repositories.TriathlonRepository;

@RestController
public class TriathlonController {

  @Autowired
  TriathlonRepository triRepo;// + setter
  
  @RequestMapping("/triathlons")
  public List<Triathlon> getTriathlonsForUser(@RequestParam(value = "user") String user) {
    ArrayList<Triathlon> tris = new ArrayList<Triathlon>();
    for (Triathlon tri : triRepo.findAll()) {
      tris.add(tri);
    }
    return tris;
  }
  
  
}
