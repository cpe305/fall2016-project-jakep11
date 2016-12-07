package app;

import model.User;

import javax.persistence.EntityExistsException;

@FunctionalInterface
public interface IUserService {
  User registerNewUserAccount(String username, String password) throws EntityExistsException;
}
