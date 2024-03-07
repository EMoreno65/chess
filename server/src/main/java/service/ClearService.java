package service;

import dataAccess.*;
import org.eclipse.jetty.server.Authentication;

import java.sql.SQLException;

public class ClearService {

  public GameDAO gameAccess = null;
  public UserDAO userAccess = null;
  public AuthDAO tokenAccess = null;

  public ClearService(){
    try {
      gameAccess = new GameSQLDAO();
      userAccess = new UserSQLDAO();
      tokenAccess = new AuthSQLDAO();
    } catch (DataAccessException e) {
      throw new RuntimeException(e);
    }
  }

  public void clearAll(UserDAO userAccess, AuthDAO tokenAccess, GameDAO gameAccess){
    try {
      userAccess.clearAll();
      gameAccess.clearAll();
      tokenAccess.clearAll();
    } catch (DataAccessException e) {
      throw new RuntimeException(e);
    }
  }

}
