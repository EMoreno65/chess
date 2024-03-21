package service;

import model.RequestandResult.LogoutResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import model.AuthData;

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
