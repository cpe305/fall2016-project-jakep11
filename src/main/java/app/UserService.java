package app;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

/**
 * Class to create a new User in the database. Checks to see if the username already exists and
 * returns null if it does.
 * 
 * @author Jake Pickett
 *
 */
@Service
public class UserService implements IUserService {
  @Autowired
  private UserRepository repository;


  @Transactional
  @Override
  public User registerNewUserAccount(String username, String password) {
    User user = new User(username, password);
    if (usernameExists(username)) {
      return null;
    }
    return repository.save(user);
  }

  private boolean usernameExists(String username) {
    // Checking if username exists
    if (!repository.findByUsername(username).isEmpty()) {
      return true;
    }
    return false;
  }
}
