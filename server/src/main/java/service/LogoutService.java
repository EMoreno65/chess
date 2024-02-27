package service;

import RequestandResult.LogoutResult;
import RequestandResult.RegisterResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;

public class LogoutService {

  public void logout(AuthDAO authDAO, UserDAO userDAO) throws DataAccessException {
      //authDAO.deleteAuth();
  }
}
