package app;

import model.User;

/**
 * Interface for registering a new User from a UserService.
 * @author Jake
 *
 */
@FunctionalInterface
public interface IUserService {
  User registerNewUserAccount(String username, String password);
}
