package dataAccessTests;

import dataAccess.AuthSQLDAO;
import dataAccess.DataAccessException;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    AuthSQLDAO authDAO = new AuthSQLDAO();
    String newToken = authDAO.generateToken();
    AuthData expectedAuthData = new AuthData(newToken, "test_user", "password123");
    authDAO.createAuth(new UserData("test_user", "password123", "email@email"));
    AuthData retrievedAuthData = authDAO.getAuthData(newToken);
    Assertions.assertEquals(expectedAuthData, retrievedAuthData, "AuthData should match");
  }

}
