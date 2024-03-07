package service;

import Request.LoginRequest;
import RequestandResult.LoginResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

public class LoginService {
  public LoginResult newResult(LoginRequest givenRequest, UserDAO userDAO, AuthDAO authDAO) throws DataAccessException {
    try{
      UserData user = userDAO.getUser(givenRequest.getUsername());
      AuthData givenToken = authDAO.createAuth(user);
      if (!userDAO.verifyPassword(user, givenRequest.getPassword())){
        DataAccessException e = new DataAccessException("Error: unauthorized");
        return new LoginResult(e.getMessage());
      }
      // I need to use getUser and createAuth to push to the database
      return new LoginResult(givenToken.getAuthToken(), givenToken.getusername()); // Only return auth and username, no need for password
    } catch(DataAccessException e) {
      return new LoginResult(e.getMessage());
    }
  }
}
