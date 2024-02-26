package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import org.eclipse.jetty.server.Authentication;

public class ClearService {

  public GameDAO gameAccess = new GameDAO();
  public UserDAO userAccess = new UserDAO();
  public AuthDAO tokenAccess = new AuthDAO();

  public void clearAll(){
    gameAccess.clearAll();
    userAccess.clearAll();
    tokenAccess.clearAll();
  }

}
