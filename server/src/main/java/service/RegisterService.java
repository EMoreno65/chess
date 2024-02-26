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
//  UserDAO userAccess = new UserDAO();
//  AuthDAO tokenAccess = new AuthDAO();
  public RegisterResult newResult(RegisterRequest givenRequest, UserDAO userDAO, AuthDAO authDAO) {
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
