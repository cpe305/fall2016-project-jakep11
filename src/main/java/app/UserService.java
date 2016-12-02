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

    System.out.println("Trying to register a new user from UserService Class");
    return repository.save(user);
  }

  private boolean usernameExists(String username) {
    System.out.println("checking if username exists");
    if (repository.findByUsername(username).size() > 0) {
      System.out.println("username exists");
      return true;
    }
    System.out.println("username does not exists");

    return false;
  }
}
