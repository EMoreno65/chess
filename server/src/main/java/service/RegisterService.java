package service;

import Request.RegisterRequest;
import RequestandResult.RegisterResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

public class RegisterService {
  public RegisterResult newResult(RegisterRequest givenRequest, UserDAO userDAO, AuthDAO authDAO) {
    String username = givenRequest.getUsername();
    String password = givenRequest.getPassword();
    String email = givenRequest.getEmail();

    if (username == null || username.isEmpty()) {
      DataAccessException e = new DataAccessException("Error: bad request");
      return new RegisterResult(e.getMessage());
    }
    if (password == null || password.isEmpty()) {
      DataAccessException e = new DataAccessException("Error: bad request");
      return new RegisterResult(e.getMessage());
    }
    if (email == null || email.isEmpty()) {
      DataAccessException e = new DataAccessException("Error: bad request");
      return new RegisterResult(e.getMessage());
    }

    UserData user = new UserData(username, password, email);
    try {
      if (userDAO.getUser(username) != null){
        DataAccessException e = new DataAccessException("Error: already taken");
        return new RegisterResult(e.getMessage());
      }
      userDAO.createUser(user);
      AuthData givenToken = authDAO.createAuth(user);
      return new RegisterResult(givenToken.getAuthToken(), givenToken.getusername(), givenToken.getPassword());
    } catch (DataAccessException e) {
      return new RegisterResult(e.getMessage());
    }
  }
}

