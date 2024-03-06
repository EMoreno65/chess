package dataAccess;

import model.AuthData;
import model.UserData;

public class AuthSQLDAO implements AuthDAO{
  @Override
  public AuthData createAuth(UserData user) throws DataAccessException {
    return null;
    // Write code in methods, written in SQL but has the same function as the memory functions
    // It's java code with sql statements
    // Find a way to convert java code in memory to java code that executes sql statement that does the same function
    // as the memory code itself. Do that for all methods
  }

  @Override
  public AuthData getAuthData(String authToken) throws DataAccessException {
    return null;
  }

  @Override
  public boolean isValidToken(String authToken) throws DataAccessException {
    return false;
  }

  @Override
  public void deleteAuth(String authToken) throws DataAccessException {

  }

  @Override
  public boolean isUserAuthenticated(String authToken) {
    return false;
  }

  @Override
  public String findUserFromAuthToken(String authToken) {
    return null;
  }

  @Override
  public void clearAll() {

  }
  // Table of AuthData
  // Create Database
  // Make table exist
  // Plus other responsibilities of DAO

}
