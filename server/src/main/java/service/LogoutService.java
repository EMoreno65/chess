package service;

import RequestandResult.LoginResult;
import RequestandResult.LogoutResult;
import RequestandResult.RegisterResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.Results;

public class LogoutService {

  public Object logout(AuthDAO authDAO, String authToken) throws DataAccessException {
    try{
      AuthData authData = authDAO.getAuthData(authToken);
      if (authData == null) {
        throw new DataAccessException("Invalid authentication token: " + authToken);
      }
      authDAO.deleteAuth(authToken);
      return null;
    }
    catch(DataAccessException e){
      return new LogoutResult(e.getMessage());
    }
  }
}
