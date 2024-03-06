package dataAccess;

import model.AuthData;
import model.UserData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class AuthDAO {

  private static ArrayList<AuthData> myTokens = new ArrayList<>();

  public AuthDAO() {
  }

  public AuthData createAuth(UserData user) throws DataAccessException {
    // Generate a unique token
    String newToken = generateToken();
    // Create AuthData object with the generated token and user information
    AuthData myToken = new AuthData(newToken, user.getUsername(), user.getPassword());
    // Add the AuthData object to the list
    myTokens.add(myToken);
    return myToken;
  }

  public AuthData getAuthData(String authToken) throws DataAccessException {
    for (AuthData authData : myTokens) {
      if (authData.getAuthToken().equals(authToken)) {
        return authData;
      }
    }
    return null;
  }

  public boolean isValidToken(String authToken) throws DataAccessException {
    // Check if AuthData object exists for the given authToken
    return getAuthData(authToken) != null;
  }

  public void deleteAuth(String authToken) throws DataAccessException {
    AuthData authDataToRemove = null;
    for (AuthData authData : myTokens) {
      if (authData.getAuthToken().equals(authToken)) {
        authDataToRemove = authData;
        break;
      }
    }

    // jdngjadfnlg

    if (authDataToRemove != null) {
      myTokens.remove(authDataToRemove);
    } else {
      throw new DataAccessException("Token not found: " + authToken);
    }
  }

  public boolean isUserAuthenticated(String authToken) {
    for (AuthData authData : myTokens) {
      if (authData.getAuthToken().equals(authToken)) {
        return true; // User is authenticated
      }
    }
    return false; // User is not authenticated
  }

  public String findUserFromAuthToken(String authToken){
    for (AuthData authData : myTokens) {
      if (authData.getAuthToken().equals(authToken)) {
        return authData.getusername(); // User is authenticated
      }
  }
    return null;
  }

  // Method to generate a random token
  private String generateToken() {
    return UUID.randomUUID().toString();
  }

  public void clearAll() {
    myTokens.clear();
  }}
