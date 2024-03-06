package service;

import dataAccess.*;
import org.eclipse.jetty.server.Authentication;

public class ClearService {

  public GameDAO gameAccess = new MemoryGameDAO();
  public UserDAO userAccess = new MemoryUserDAO();
  public AuthDAO tokenAccess = new MemoryAuthDAO();

  public void clearAll(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO){
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }

}
