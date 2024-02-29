package serverTests;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.RegisterService;
import Request.RegisterRequest;
import RequestandResult.RegisterResult;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTests {

  private RegisterService registerService;
  private UserDAO userDAO;
  private AuthDAO authDAO;

  @BeforeEach
  public void setUp() {
    userDAO = new UserDAO();
    authDAO = new AuthDAO();
    registerService = new RegisterService();
  }

  @Test
  public void testRegister_Success() {
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";
    RegisterRequest registerRequest = new RegisterRequest(username, password, email);
    RegisterResult registerResult = registerService.newResult(registerRequest, userDAO, authDAO);
    assertNotNull(registerResult.getAuthToken());
    assertEquals(username, registerResult.getUsername());
    assertEquals(password, registerResult.getPassword());
    assertNull(registerResult.getErrorMessage());
    userDAO.clearAll();
    authDAO.clearAll();
  }

  @Test
  public void testRegister_Failure() {
    // No Email
    String username = null;
    String password = "";
    String email = "ylfjfcktetf";
    RegisterRequest registerRequest = new RegisterRequest(username, password, email);

    RegisterResult registerResult = registerService.newResult(registerRequest, userDAO, authDAO);

    assertNull(registerResult.getAuthToken(), "Auth token should not be generated");
    assertNull(registerResult.getUsername(), "Username should not be returned");
    assertNull(registerResult.getPassword(), "Password should not be returned");
    assertNotNull(registerResult.getErrorMessage(), "Error message should be present");
    userDAO.clearAll();
    authDAO.clearAll();
  }
}

