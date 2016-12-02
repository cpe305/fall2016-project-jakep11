package app;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    List<User> users = userRepo.findByUsername(username);
    if (users.size() < 1) {
      return null;
    }
    System.out.println("found user");
    System.out.println(users.get(0).getPassword());

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
