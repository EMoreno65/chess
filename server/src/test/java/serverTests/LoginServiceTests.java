package serverTests;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LoginService;
import Request.LoginRequest;
import RequestandResult.LoginResult;
import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTests {

  private LoginService loginService;
  private UserDAO userDAO;
  private AuthDAO authDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    // Clear existing test data
    // Initialize the login service and required DAOs
    userDAO = new UserDAO();
    authDAO = new AuthDAO();
    loginService = new LoginService();

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String authToken = "givenAuthToken";
    String email = "email@email";

    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);

    AuthData authData = new AuthData(authToken, username, password); // Use the same password
    authDAO.createAuth(user);
  }

  private void clearData() {
    // Clear existing test data from the database
    userDAO.clearAll(); // Implement clearAll() method in UserDAO to clear all users
    authDAO.clearAll(); // Implement clearAll() method in AuthDAO to clear all authentication data
  }


  @Test
  public void testLogin_Failure_IncorrectPassword() throws DataAccessException {
    // Arrange: Set up test data
    String username="testUser";
    String correctPassword="testPassword";
    String incorrectPassword="wrongPassword";
    String authToken="givenAuthToken";
    LoginRequest loginRequest=new LoginRequest(username, authToken, incorrectPassword);

    // Act: Call the login method
    LoginResult loginResult=loginService.newResult(loginRequest, userDAO, authDAO);

    // Assert: Verify the result
    assertNull(loginResult.getAuthToken(), "Auth token should not be generated");
    assertNotNull(loginResult.getErrorMessage(), "Error message should be present");
    assertEquals("Error: unauthorized", loginResult.getErrorMessage(), "Error message should indicate unauthorized access");
    userDAO.clearAll();
    authDAO.clearAll();
  }

  @Test
  public void testLogin_Success() throws DataAccessException {
    // Arrange: Set up test data
    String username = "testUser";
    String password = "testPassword";
    String authToken = "givenAuthToken";
    LoginRequest loginRequest = new LoginRequest(username, authToken, password);

    // Act: Call the login method
    LoginResult loginResult = loginService.newResult(loginRequest, userDAO, authDAO);

    // Assert: Verify the result
    assertNotNull(loginResult.getAuthToken(), "Auth token should be generated");
    assertNull(loginResult.getErrorMessage(), "No error message should be present");
    userDAO.clearAll();
    authDAO.clearAll();
  }
}

