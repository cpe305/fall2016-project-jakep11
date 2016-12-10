package app;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to authenticate a user's credentials.
 * @author Jake Pickett
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  UserRepository userRepo;// + setter

  @Override
  public Authentication authenticate(Authentication authentication) {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    List<User> users = userRepo.findByUsername(username);
    if (users.isEmpty()) {
      return null;
    }
    
    if (!BCrypt.checkpw(password, users.get(0).getPassword())) {
      return null;
    }

    // use the credentials and authenticate against the third-party system
    return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
