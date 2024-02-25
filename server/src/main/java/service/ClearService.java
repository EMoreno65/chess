package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import org.eclipse.jetty.server.Authentication;

public class ClearService {

  GameDAO gameAccess = new GameDAO();
  UserDAO userAccess = new UserDAO();
  AuthDAO tokenAccess = new AuthDAO();

  public void clearAll(){
    gameAccess.clearAll();
    userAccess.clearAll();
    tokenAccess.clearAll();
  }

}
