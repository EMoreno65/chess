package serviceTests;

import dataAccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LoginService;
import model.Request.LoginRequest;
import model.RequestandResult.LoginResult;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTests {

  private LoginService loginService;
  private UserDAO userDAO;
  private AuthDAO authDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    userDAO = new MemoryUserDAO();
    authDAO = new MemoryAuthDAO();
    loginService = new LoginService();

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String authToken = "givenAuthToken";
    String email = "email@email";

    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);

    AuthData authData = new AuthData(authToken, username, password);
    authDAO.createAuth(user);
  }

  private void clearData(){
    // Clear existing test data from the database
    try {
      userDAO.clearAll();
      authDAO.clearAll();
    }
    catch (DataAccessException e){
      throw new RuntimeException(e);
    }
  }


  @Test
  public void testLogin_Failure_IncorrectPassword() throws DataAccessException, SQLException {
    // Arrange: Set up test data
    String username="testUser";
    String correctPassword="testPassword";
    String incorrectPassword="wrongPassword";
    String authToken="givenAuthToken";
    LoginRequest loginRequest=new LoginRequest(username, authToken, incorrectPassword);


    LoginResult loginResult=loginService.newResult(loginRequest, userDAO, authDAO);

    assertNull(loginResult.getAuthToken(), "Auth token should not be generated");
    assertNotNull(loginResult.getErrorMessage(), "Error message should be present");
    assertEquals("Error: unauthorized", loginResult.getErrorMessage(), "Error message should indicate unauthorized access");
    userDAO.clearAll();
    authDAO.clearAll();
  }

  @Test
  public void testLogin_Success() throws DataAccessException, SQLException {

    String username = "testUser";
    String password = "testPassword";
    String authToken = "givenAuthToken";
    LoginRequest loginRequest = new LoginRequest(username, authToken, password);

    LoginResult loginResult = loginService.newResult(loginRequest, userDAO, authDAO);

    assertNotNull(loginResult.getAuthToken(), "Auth token should be generated");
    assertNull(loginResult.getErrorMessage(), "No error message should be present");
    userDAO.clearAll();
    authDAO.clearAll();
  }
}

