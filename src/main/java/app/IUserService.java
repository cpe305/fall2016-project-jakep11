package app;

import model.User;

import javax.persistence.EntityExistsException;

public interface IUserService {
  User registerNewUserAccount(String username, String password) throws EntityExistsException;
}
