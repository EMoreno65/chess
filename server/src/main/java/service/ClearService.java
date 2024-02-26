package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import org.eclipse.jetty.server.Authentication;

public class ClearService {

  public GameDAO gameAccess = new GameDAO();
  public UserDAO userAccess = new UserDAO();
  public AuthDAO tokenAccess = new AuthDAO();

  public void clearAll(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO){
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }

}
