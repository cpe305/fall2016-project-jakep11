package app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.User;
import repositories.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  //This class allows you to log in
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Trying to log in a user from MyUserDetailsService Class");

    List<User> users = userRepository.findByUsername(username);
    System.out.println("AFter Trying to log in a user from MyUserDetailsService Class");

    if (users.size() == 0) {
      System.out.println("No user found with username: " + username);
      throw new UsernameNotFoundException("No user found with username: " + username);
    }
    
    System.out.println("found one");
    User user = users.get(0);
    System.out.println("Password already saved:" + user.getPassword());
    System.out.println(user.getUsername());
    System.out.println(user.toString());

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    System.out.println("Right before 'return'");

    /*return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, getAuthorities(user.getRoles()));*/
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, getAuthorities(user.getRoles()));
  }

  private static List<GrantedAuthority> getAuthorities(List<String> roles) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
      System.out.println("found role");
    }
    return authorities;
  }
}
