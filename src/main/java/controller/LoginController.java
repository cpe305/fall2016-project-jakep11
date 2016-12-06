package controller;

import app.UserService;
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



import java.security.Principal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;



@RestController
public class LoginController {
  @Autowired
  private UserService service;

  /** registerUserAccount creates a new user.
   * @param username username
   * @param password password
   * @param response response
   * @return returns the created user
   * @throws Exception exception
   */
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
    return user;
  }

}
