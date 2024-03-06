package dataAccess;

import model.UserData;

public class UserSQLDAO implements UserDAO{
  // Table of UserData
  // convert to SQL using executeUpdate?
  public void createUser(UserData user) throws DataAccessException {
  }

  public UserData getUser(String username) throws DataAccessException {
    return null;
  }

  public String getPassword(String username) throws DataAccessException{
    return null;
  }

  public boolean verifyPassword(UserData user, String providedPassword) {
    return false;
  }


  // Write get Password method that does the same thing as get User


  public void clearAll() {
  }
}
