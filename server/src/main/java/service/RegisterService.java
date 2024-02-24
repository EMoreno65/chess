package service;

import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.UserData;

public class RegisterService {
  UserDAO userAccess = new UserDAO();
  AuthDAO tokenAccess = new AuthDAO();
  public RegisterResult newResult(RegisterRequest givenRequest) {
    String username =givenRequest.getUsername();
    RegisterResult myResult = new RegisterResult();
    UserData user = new UserData(givenRequest.getUsername(), givenRequest.getPassword(), givenRequest.getEmail());
    try{
      userAccess.addUser(user);
      myResult.setMyToken(tokenAccess.createAuth(user));
      return myResult;
    } catch(DataAccessException e) {
      myResult.setErrorMessage(e.getMessage());
      return myResult;
    }
  }
}
