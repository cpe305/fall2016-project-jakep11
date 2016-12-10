package app;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that assists in login authentication and returns the Spring security context.
 * @author Jake Pickett
 *
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;


  /** 
   * UserDetails method that helps authentication during log in.
   * @param username username to search for in the database of users
   */
  
  @Override
  public UserDetails loadUserByUsername(String username) {

    List<User> users = userRepository.findByUsername(username);

    if (users.isEmpty()) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }

    User user = users.get(0);

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, getAuthorities(user.getRoles()));
  }

  private static List<GrantedAuthority> getAuthorities(List<String> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }
}
