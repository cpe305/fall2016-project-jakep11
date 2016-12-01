package app;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import model.User;
import repositories.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  UserRepository userRepo;// + setter

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    System.out.println("Authenticating user");
    System.out.println(username);
    System.out.println(password);

    boolean isValidUser = true;
    List<User> users = userRepo.findByUsername(username);
    if (users.size() < 1) {
      return null;
    }
    System.out.println("found user");
    System.out.println(users.get(0).getPassword());

    if (!BCrypt.checkpw(password, users.get(0).getPassword())) {
      return null;
    }

    if (isValidUser) {
      System.out.println("IS VALID USER");

      // use the credentials and authenticate against the third-party system
      return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    } else {
      return null;
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
