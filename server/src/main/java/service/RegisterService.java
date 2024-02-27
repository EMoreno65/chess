package service;

import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

public class RegisterService {
  public RegisterResult newResult(RegisterRequest givenRequest, UserDAO userDAO, AuthDAO authDAO) {
    String username = givenRequest.getUsername();
    String password = givenRequest.getPassword();
    String email = givenRequest.getEmail();

    if (password == null || password.isEmpty()) {
      DataAccessException e = new DataAccessException("Error: bad request");
      return new RegisterResult(e.getMessage());
    }

    UserData user = new UserData(username, password, email);
    try {
      userDAO.createUser(user);
      AuthData givenToken = authDAO.createAuth(user);
      return new RegisterResult(givenToken.getAuthToken(), givenToken.getusername(), givenToken.getPassword());
    } catch (DataAccessException e) {
      return new RegisterResult(e.getMessage());
    }
  }
}

