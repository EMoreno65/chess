package dataAccess;

import model.AuthData;
import model.UserData;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO {

  private static HashMap<String, UserData> myUsers = new HashMap<String, UserData>();

  public UserDAO() {
  }

  public void addUser(UserData user) throws DataAccessException{
    if(myUsers.get(user.getUsername()) == null){
      myUsers.put(user.getUsername(), user);
    }
    else{
      throw new DataAccessException("Username already exists");
    }
    // Check if username already exists
    // If not, add user data to the hash map
  }

  public void clearAll() {
  }
}
