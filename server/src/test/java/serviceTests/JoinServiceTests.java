package serviceTests;

import RequestandResult.JoinResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.JoinService;
import Request.JoinRequest;
import chess.ChessGame;
import static org.junit.jupiter.api.Assertions.*;

public class JoinServiceTests {

  private JoinService joinService;
  private AuthDAO authDAO;
  private GameDAO gameDAO;
  private UserDAO userDAO;

  @BeforeEach
  public void setUp() {
    // Initialize necessary objects
    authDAO = new AuthDAO();
    gameDAO = new GameDAO();
    userDAO = new UserDAO();
    joinService = new JoinService();
  }

  @Test
  public void testJoinGame_Success() throws DataAccessException {
    // Arrange: Set up test data
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";

    // Create a user and associate it with a valid authentication token
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData validAuthData = authDAO.createAuth(user);
    String validAuthToken = validAuthData.authToken();
    GameData game = new GameData(1, null, null, "Test Game", new ChessGame());
    gameDAO.createGame(game.gameID(), game);
    JoinRequest joinRequest = new JoinRequest(validAuthToken, ChessGame.TeamColor.WHITE, game.gameID());
    JoinResult joinResult = (JoinResult) joinService.joinResult(joinRequest, userDAO, authDAO, gameDAO);

    // Assert: Verify the result
    assertNotNull(joinResult.getAuthToken(), "Auth token should be present");
    assertNotNull(joinResult.getGameData(), "Game data should be present");
    assertNull(joinResult.getErrorMessage(), "No error message should be present");
    authDAO.clearAll();
    userDAO.clearAll();
    gameDAO.clearAll();
  }

  @Test
  public void testJoinGame_Failure_AlreadyTaken() throws DataAccessException {
    // Arrange: Set up test data
    String authToken = "validAuthToken";
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";

    // Create a user and associate it with a valid authentication token
    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    authDAO.createAuth(user);

    // Create a game with a player already assigned to the white team
    GameData game = new GameData(1, "anotherUser", null, "Test Game", new ChessGame());
    gameDAO.createGame(game.gameID(), game);

    // Create a join request for the white team
    JoinRequest joinRequest = new JoinRequest(authToken, ChessGame.TeamColor.WHITE, game.gameID());
    JoinResult joinResult = (JoinResult) joinService.joinResult(joinRequest, userDAO, authDAO, gameDAO);
    assertNotNull(joinResult.getErrorMessage(), "Error message should be present");
    assertNull(joinResult.getAuthToken(), "Auth token should not be present");
    assertNull(joinResult.getGameData(), "Game data should not be present");
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }
}

