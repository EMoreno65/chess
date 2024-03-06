package serviceTests;

import RequestandResult.ListResult;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ListService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListServiceTests {

  private ListService listService;
  private AuthDAO authDAO;
  private GameDAO gameDAO;
  private UserDAO userDAO;

  @BeforeEach
  public void setUp() {
    // Initialize necessary objects
    authDAO = new MemoryAuthDAO();
    gameDAO = new GameDAO();
    userDAO = new MemoryUserDAO();
    listService = new ListService();
  }

  @Test
  public void testListGames_Success() throws DataAccessException {
    // Arrange: Set up test data
    List<GameData> expectedGames = new ArrayList<>();
    expectedGames.add(new GameData(1, "Game 1", null, null, null));
    expectedGames.add(new GameData(2, "Game 2", null, null, null));
    for (GameData game : expectedGames) {
      gameDAO.createGame(game.gameID(), game);
    }

    ListResult listResult = listService.listResult(authDAO, gameDAO);

    assertEquals(expectedGames, listResult.getGames(), "List of games should match");
    assertEquals(null, listResult.getErrorMessage(), "No error message should be present");
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }
  @Test
  public void testListGames_NoGames() throws DataAccessException {
    String username = "testUser";
    String password = "testPassword";
    String email = "test@example.com";

    UserData user = new UserData(username, password, email);
    userDAO.createUser(user);
    AuthData validAuthData = authDAO.createAuth(user);
    String validAuthToken = validAuthData.getAuthToken();

    // Do not create any games

    // Attempt to list games
    ListResult listResult = listService.listResult(authDAO, gameDAO);

    assertNull(listResult.getErrorMessage(), "No error message should be present");
    assertEquals(new ArrayList<>(), listResult.getGames(), "Returned list should be empty");
    userDAO.clearAll();
    gameDAO.clearAll();
    authDAO.clearAll();
  }

}


