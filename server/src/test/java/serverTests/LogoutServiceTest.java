package serverTests;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LogoutService;
import RequestandResult.LogoutResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutServiceTest {

  private LogoutService logoutService;
  private AuthDAO authDAO;
  private UserDAO userDAO;
  private String validAuthToken;
  private java.util.UUID UUID;

  @BeforeEach
  public void setUp() throws DataAccessException {
    authDAO = new AuthDAO(); // Assuming authDAO initialization
    userDAO = new UserDAO(); // Assuming userDAO initialization
    logoutService = new LogoutService();

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData authDataForToken = authDAO.createAuth(user); // Use the authData with the generated token
    validAuthToken = authDataForToken.authToken();
    // Generate a unique authentication token
    // Create a test authentication data entry with the generated authentication token
    AuthData authData = new AuthData(validAuthToken, username, password); // Use the generated token
  }

  private void clearData() {
    // Clear existing test data from the database
    userDAO.clearAll(); // Implement clearAll() method in UserDAO to clear all users
    authDAO.clearAll(); // Implement clearAll() method in AuthDAO to clear all authentication data
  }

  private String generateUniqueAuthToken() {
    // Generate a unique authentication token (you can use any method to generate unique strings)
    // For simplicity, you can use a random UUID
    return UUID.randomUUID().toString();
  }


  @Test
  public void testLogout_Success() throws DataAccessException {
    // Act: Call the logout method with the valid authentication token
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
  public void testLogout_Failure_InvalidAuthToken() throws DataAccessException {
    // Arrange: Set up test data
    String invalidAuthToken = "invalidAuthToken"; // Assuming an invalid authentication token

    // Act: Call the logout method
    LogoutResult logoutResult = (LogoutResult) logoutService.logout(authDAO, invalidAuthToken);

    // Assert: Verify the result
    assertNotNull(logoutResult.getErrorMessage(), "Error message should be present");
    assertEquals("Invalid authentication token: " + invalidAuthToken, logoutResult.getErrorMessage(), "Error message should indicate invalid token");
    assertNull(logoutResult.getAuthToken(), "AuthToken should not be present in case of failure");
    assertNull(logoutResult.getUsername(), "Username should not be present in case of failure");
    clearData();
  }
}

