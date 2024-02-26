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
      throw new DataAccessException("User already exists with username: " + user.getUsername());
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

  public UserData getPassword(String password) throws DataAccessException{
    UserData user = myUsers.get(password);
    if (user == null){
      throw new DataAccessException("Password is Incorrect");
    }
    return user;
  }

  // Write get Password method that does the same thing as get User


  public void clearAll() {
  }
}
