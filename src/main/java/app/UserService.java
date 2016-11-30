package app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.User;
import repositories.UserRepository;

@Service
public class UserService implements IUserService{
  @Autowired
  private UserRepository repository;

  
  @Transactional
  @Override
  public User registerNewUserAccount(String username, String password){

    User user = new User(username, password);
    /*user.setFirstName(accountDto.getFirstName());
    user.setLastName(accountDto.getLastName());
    user.setPassword(accountDto.getPassword());
    user.setEmail(accountDto.getEmail());
    user.setRoles(Arrays.asList("ROLE_USER"));*/
    System.out.println("Trying to register a new user from UserService Class");
    return repository.save(user);
  }

  private boolean emailExist(String email) {
    User user = repository.findByUsername(email).get(0);
    if (user != null) {
      return true;
    }
    return false;
  }
}
