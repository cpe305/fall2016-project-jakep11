package app;

import javax.persistence.EntityExistsException;

import model.User;

public interface IUserService {
  User registerNewUserAccount(String username, String password)     
    throws EntityExistsException;
}
