package service;

import RequestandResult.LoginRequest;
import RequestandResult.LoginResult;
import dataAccess.UserDAO;

public class LoginService {
  //  UserDAO userAccess = new UserDAO();
//  AuthDAO tokenAccess = new AuthDAO();
  public LoginResult newResult(LoginRequest givenRequest, UserDAO userDAO) {
    UserData user = new UserData(givenRequest.getUsername(), givenRequest.getPassword(), givenRequest.getEmail());
    try{
      userDAO.createUser(user);
      AuthData givenToken = authDAO.createAuth(user);
      return new RegisterResult(givenToken.getAuthToken(), givenToken.getusername());
    } catch(DataAccessException e) {
      return new RegisterResult(e.getMessage());
    }
  }
}
