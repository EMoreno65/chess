package serviceTests;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Request.CreateRequest;
import RequestandResult.CreateResult;
import chess.ChessGame;
import model.GameData;
import service.CreateService;

import static org.junit.jupiter.api.Assertions.*;

public class CreateServiceTests {

  private CreateService createService;
  private AuthDAO authDAO;
  private GameDAO gameDAO;
  private UserDAO userDAO;
  private String validAuthToken;

  @BeforeEach
  public void setUp() {
    // Initialize necessary objects
    authDAO = new AuthDAO(); // Assuming authDAO initialization
    gameDAO = new GameDAO(); // Assuming gameDAO initialization
    userDAO = new UserDAO(); // Assuming userDAO initialization
    createService = new CreateService();
  }

  @Test
  public void testCreateGame_Success() throws DataAccessException {
    // Arrange: Set up test data and parameters
    String gameName = "Test Game";

    // Create a test user
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData authDataForToken = authDAO.createAuth(user);
    validAuthToken = authDataForToken.authToken();
    int gameID = 5;
    String whiteUsername = "Hey";
    String blackUsername = "Hello";
    ChessGame game = new ChessGame();
    GameData gameData = new GameData(gameID, whiteUsername, blackUsername, gameName, game);

    // Create a test authentication data entry
    AuthData authData = new AuthData(validAuthToken, username, password);
    authDAO.createAuth(user);

    CreateRequest createRequest = new CreateRequest(validAuthToken, gameName, gameData, new ChessGame());

    // Act: Call the createGame method with the test data and parameters
    CreateResult createResult = createService.createGame(createRequest, authDAO, gameDAO, userDAO);

    // Assert: Verify the result
    assertNotNull(createResult, "Result should not be null");
    assertNull(createResult.getErrorMessage(), "No error should occur");
    userDAO.clearAll();
    authDAO.clearAll();
    gameDAO.clearAll();
  }


  @Test
  public void testCreateGame_Unauthorized() throws DataAccessException {
    // Arrange: Set up test data and parameters for unauthorized scenario
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData authDataForToken = authDAO.createAuth(user);
    validAuthToken = authDataForToken.authToken();
    String invalidAuthToken = "badAuthTokenStayAway";
    String gameName = "Test Game";
    int gameID = 4;
    String whiteUsername = "Sup";
    String blackUsername = "What's Good";
    ChessGame game = new ChessGame();
    GameData gameData = new GameData(gameID, whiteUsername, blackUsername, gameName, game);
    AuthData authData = new AuthData(validAuthToken, username, password);
    CreateRequest createRequest = new CreateRequest(invalidAuthToken, gameName, gameData, new ChessGame());

    // Act: Call the createGame method with the test data and parameters
    CreateResult createResult = createService.createGame(createRequest, authDAO, gameDAO, userDAO);

    // Assert: Verify that the method returns an error result
    assertNotNull(createResult.getErrorMessage(), "An error should occur");
    assertEquals("Error: unauthorized", createResult.getErrorMessage(), "Error message should indicate unauthorized access");
    assertEquals(createResult.getGameID(), 0);
    userDAO.clearAll();
    authDAO.clearAll();
    gameDAO.clearAll();
  }
}
