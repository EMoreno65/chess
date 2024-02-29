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
    // Initialize the login service and required DAOs
    userDAO = new UserDAO();
    authDAO = new AuthDAO();
    loginService = new LoginService();

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String authToken = "givenAuthToken"; // Include the authToken
    UserData user = new UserData(username, password, authToken);
    userDAO.createUser(user); // You need to implement this method in UserDAO
    AuthData authData = new AuthData(authToken, username, password); // Assuming you have AuthData class to represent authentication data
    authDAO.createAuth(user); // Assuming you have a method to create authentication data in AuthDAO
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
  }
}

