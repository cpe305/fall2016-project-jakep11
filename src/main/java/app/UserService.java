package app;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

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
    //Checking if username exists
    if (!repository.findByUsername(username).isEmpty()) {
      return true;
    }
    return false;
  }
}
