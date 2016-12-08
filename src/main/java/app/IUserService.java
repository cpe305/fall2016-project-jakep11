package app;

import model.User;

@FunctionalInterface
public interface IUserService {
  User registerNewUserAccount(String username, String password);
}
