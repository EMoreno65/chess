package dataAccessTests;

import dataAccess.AuthSQLDAO;
import dataAccess.DataAccessException;
import dataAccess.DatabaseManager;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.asm.ClassReader;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthSQLDAOTest {

  // Positive Test Case
  @Test
  public void testCreateAuth_Positive() throws DataAccessException {
    UserData user = new UserData("test_user", "password123", "email@whatsayyeconcerningsuchmatters.cool");

    AuthSQLDAO authDAO = new AuthSQLDAO();

    AuthData authData = null;
    try {
      authData = authDAO.createAuth(user);
    } catch (DataAccessException e) {
      throw new RuntimeException(e);
    }

    // Check if authData is not null
    Assertions.assertNotNull(authData, "AuthData should not be null");
    // Make sure it has the right data
    Assertions.assertEquals("test_user", authData.getusername(), "Username should match");
    Assertions.assertEquals("password123", authData.getPassword(), "Password should match");
  }
  @Test
  public void testCreateAuth_Positive2() throws DataAccessException {
    UserData user = new UserData("dbslkfds", "dsayfgaldshfds", "dsklgsdhfdskj");

    // Create an instance of AuthSQLDAO
    AuthSQLDAO authDAO = new AuthSQLDAO();
    AuthData authData = null;
    try {
      authData = authDAO.createAuth(user);
    } catch (DataAccessException e) {
      throw new DataAccessException("No username");
    }
    Assertions.assertNotNull(authData, "AuthData should not be null");

    Assertions.assertEquals("dbslkfds", authData.getusername(), "Username should match");
    Assertions.assertEquals("dsayfgaldshfds", authData.getPassword(), "Password should match");
  }

  @Test
  public void testGetAuthData_Positive() throws DataAccessException {
    // Create an instance of AuthSQLDAO
    AuthSQLDAO authDAO = new AuthSQLDAO();

    // Generate a new authentication token

    // Create a new user and insert authentication data with the generated token
    UserData user = new UserData("test_user", "password123", "email@email");
    AuthData authData = authDAO.createAuth(user);
    String newToken = authData.authToken();

    // Retrieve authentication data using the same token that was generated
    AuthData retrievedAuthData = authDAO.getAuthData(newToken);

    // Create an expected AuthData object with the user's details
    AuthData expectedAuthData = new AuthData(newToken, user.getUsername(), user.getPassword());

    // Perform assertion to compare the expected and retrieved AuthData objects
    Assertions.assertEquals(expectedAuthData, retrievedAuthData, "AuthData should match");
  }
  @Test
  public void testGetAuthData_Negative() throws DataAccessException {
    // Create an instance of AuthSQLDAO
    AuthSQLDAO authDAO = new AuthSQLDAO();

    // Generate a new authentication token

    // Create a new user and insert authentication data with the generated token
    UserData user = new UserData("test_user", "password123", "email@email");
    AuthData authData = authDAO.createAuth(user);
    String newToken =authDAO.generateToken();

    // Retrieve authentication data using the same token that was generated
    AuthData retrievedAuthData = authDAO.getAuthData(newToken);

    // Create an expected AuthData object with the user's details
    AuthData expectedAuthData = new AuthData(authData.authToken(), user.getUsername(), user.getPassword());

    // Perform assertion to compare the expected and retrieved AuthData objects
    Assertions.assertNull(retrievedAuthData, "AuthData should match");
  }

  @Test
  public void testIsValidTokenTrue() throws DataAccessException {
    AuthSQLDAO authDAO = new AuthSQLDAO();
    UserData user = new UserData("test_user", "password123", "email@email");
    AuthData authData = authDAO.createAuth(user);
    String newToken = authData.authToken();
    assertTrue(authDAO.isValidToken(newToken), "Token is Valid");
  }
  @Test
  public void testIsValidTokenFalse() throws DataAccessException {
    AuthSQLDAO authDAO = new AuthSQLDAO();
    UserData user = new UserData("test_user", "password123", "email@email");
    AuthData authData = authDAO.createAuth(user);
    String newToken =authDAO.generateToken();
    assertFalse(authDAO.isValidToken(newToken), "Token is invalid");
  }


}
