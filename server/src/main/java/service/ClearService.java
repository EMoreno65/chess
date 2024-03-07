package service;

import dataAccess.*;
import org.eclipse.jetty.server.Authentication;

public class ClearService {

  public GameDAO gameAccess = new GameSQLDAO();
  public UserDAO userAccess = new UserSQLDAO();
  public AuthDAO tokenAccess = new AuthSQLDAO();

  public void clearAll(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO){
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }

}
