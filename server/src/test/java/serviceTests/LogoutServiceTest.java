package serviceTests;

import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LogoutService;
import RequestandResult.LogoutResult;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutServiceTest {

  private LogoutService logoutService;
  private AuthDAO authDAO;
  private UserDAO userDAO;
  private String validAuthToken;
  private java.util.UUID UUID;

  @BeforeEach
  public void setUp() throws DataAccessException {
    authDAO = new MemoryAuthDAO();
    userDAO = new MemoryUserDAO();
    logoutService = new LogoutService();

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData authDataForToken = authDAO.createAuth(user);
    validAuthToken = authDataForToken.authToken();
    AuthData authData = new AuthData(validAuthToken, username, password);
  }

  private void clearData() throws DataAccessException, SQLException {
    userDAO.clearAll();
    authDAO.clearAll();
  }

  private String generateUniqueAuthToken() {
    return UUID.randomUUID().toString();
  }


  @Test
  public void testLogout_Success() throws DataAccessException, SQLException {

    Object result = logoutService.logout(authDAO, validAuthToken);
    if (result instanceof LogoutResult) {
      LogoutResult logoutResult = (LogoutResult) result;
      assertNull(logoutResult.getErrorMessage(), "No error message should be present");
      assertEquals(validAuthToken, logoutResult.getAuthToken(), "Returned authToken should match input authToken");
      assertNotNull(logoutResult.getUsername(), "Username should be present in the result");
    } else {
      assertNull(result, "Logout result should be null");
    }
    clearData();
  }



  @Test
  public void testLogout_Failure_InvalidAuthToken() throws DataAccessException, SQLException {

    String invalidAuthToken = "invalidAuthToken"; // Assuming an invalid authentication token

    LogoutResult logoutResult = (LogoutResult) logoutService.logout(authDAO, invalidAuthToken);

    assertNotNull(logoutResult.getErrorMessage(), "Error message should be present");
    assertEquals("Invalid authentication token: " + invalidAuthToken, logoutResult.getErrorMessage(), "Error message should indicate invalid token");
    assertNull(logoutResult.getAuthToken(), "AuthToken should not be present in case of failure");
    assertNull(logoutResult.getUsername(), "Username should not be present in case of failure");
    clearData();
  }
}

