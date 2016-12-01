package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import app.*;
import model.User;
import repositories.UserRepository;

import java.security.Principal;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



@RestController
public class LoginController {
  @Autowired
  private UserService service;


  @RequestMapping(path = "/login", method = RequestMethod.POST)
  public String login(Principal principal, @RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password) {

    // Authentication auth =
    // new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    System.out.println(principal.getName());
    SecurityContextHolder.getContext().setAuthentication((Authentication) principal);

    return "hello";
  }


  @RequestMapping(value = "/createUser", method = RequestMethod.POST)
  public User registerUserAccount(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password, HttpServletResponse response)
      throws Exception {

    User registered =
        service.registerNewUserAccount(username, BCrypt.hashpw(password, BCrypt.gensalt()));

    if (registered == null) {
      response.setStatus(401);
      return null;
    }

    Authentication auth =
        new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());


    SecurityContextHolder.getContext().setAuthentication(auth);
    return registered;
  }



  @RequestMapping("/user")
  public Principal user(Principal user) {
    System.out.println("logging in?");
    return user;
  }

}
