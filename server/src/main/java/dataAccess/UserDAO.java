package dataAccess;

import chess.ChessGame;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO {

  private static HashMap<String, UserData> myUsers = new HashMap<String, UserData>();

  public UserDAO() {
  }

  public void createUser(UserData user) throws DataAccessException {
    if (myUsers.containsKey(user.getUsername())) {
      throw new DataAccessException("Error: already taken");
    }
    myUsers.put(user.getUsername(), user);
  }

  public UserData getUser(String username) throws DataAccessException {
    UserData user = myUsers.get(username);
    if (user == null) {
      throw new DataAccessException("User not found with username: " + username);
    }
    return user;
  }

  public String getPassword(String username) throws DataAccessException{
    for (UserData user : myUsers.values()) {
      if (user.getUsername().equals(username)) {
        return user.password();
      }
    }
    throw new DataAccessException("User not found with username: " + username);
  }

  public boolean verifyPassword(UserData user, String providedPassword) {
    String username = user.getUsername();
    String storedPassword = user.password();

    if (storedPassword != null) {
      return storedPassword.equals(providedPassword);
    }
    return false;
  }


  // Write get Password method that does the same thing as get User


  public void clearAll() {
  }
}
