package dataAccess;

import chess.ChessGame;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDAO {
  public void createUser(UserData user) throws DataAccessException;

  public UserData getUser(String username) throws DataAccessException;

  public String getPassword(String username) throws DataAccessException;

  public boolean verifyPassword(UserData user, String providedPassword);

  public void clearAll();
}
