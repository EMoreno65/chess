package dataAccessTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameSQLDAOTest {

  GameDAO gameDAO = new GameSQLDAO();

  public GameSQLDAOTest() throws DataAccessException {
  }

  @BeforeEach
  public void clearAll() throws DataAccessException {
    gameDAO.clearAll();
  }
  @Test
  public void testCreateGamePositive() throws DataAccessException {
      int gameId = 1;
      ChessGame game1 = new ChessGame();
      GameData game = new GameData(gameId, "white_username", "black_username", "game_name", game1);

      GameDAO gameDAO = new GameSQLDAO();
      // Execute the createGame method and assert that no exception is thrown
      Assertions.assertDoesNotThrow(() -> {
        gameDAO.createGame(gameId, game);
      }, "No exception should be thrown when creating a game");
    }
  @Test
  public void testCreateGameNegative() throws DataAccessException {
    int gameId = 1;
    ChessGame game1 = new ChessGame();
    GameData game = new GameData(gameId, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "black_username", "game_name", game1);

    GameDAO gameDAO = new GameSQLDAO();
    // Execute the createGame method and assert that no exception is thrown
    Assertions.assertThrows(DataAccessException.class, () -> {
      gameDAO.createGame(gameId, game);
    });
  }

  @Test
  public void testGetGame_Positive() throws DataAccessException {
    GameSQLDAO gameDAO = new GameSQLDAO();
    int gameId = 6;

    ChessGame game1 = new ChessGame();
    GameData game = new GameData(gameId, "white", "black", "name", game1);

    // Create the game in the database
    gameDAO.createGame(gameId, game);

    // Retrieve the game from the database
    GameData retrievedGame = gameDAO.getGame(gameId);

    GameData expectedGameData = new GameData(gameId, "white", "black", "name", game1);
    Assertions.assertEquals(expectedGameData, retrievedGame, "GameData should match");
  }

  @Test
  public void testGetGame_Negative() throws DataAccessException {
    GameSQLDAO gameDAO = new GameSQLDAO();
    int gameId = -6;

    ChessGame game1 = new ChessGame();
    GameData game = new GameData(gameId, "white", "black", "name", game1);

    // Retrieve the game from the database
    Assertions.assertThrows(DataAccessException.class, () -> {
      gameDAO.getGame(gameId);
    });
  }

  @Test
  public void testGetGameID_Positive() throws DataAccessException {
    ChessGame gameToFind = new ChessGame();
    GameDAO gameDAO = new GameSQLDAO();
    int expectedGameID = 1;
    gameDAO.createGame(expectedGameID, new GameData(expectedGameID, "white", "black", "name", gameToFind));
    int retrievedGameID = gameDAO.getGameID(gameToFind);
    Assertions.assertEquals(expectedGameID, retrievedGameID, "Retrieved game ID should match the expected game ID");
  }
  @Test
  public void testGetGameID_Negative() throws DataAccessException {
    ChessGame gameToFind = new ChessGame();
    GameDAO gameDAO = new GameSQLDAO();
    int expectedGameID = 1;
    gameDAO.createGame(expectedGameID, new GameData(expectedGameID, "white", "black", "name", gameToFind));
    int retrievedGameID = 7;
    Assertions.assertNotEquals(expectedGameID, retrievedGameID, "Retrieved game ID should not match the expected game ID");
  }

  @Test
  public void testListGamesPositive() throws DataAccessException, SQLException {
    GameDAO gameDAO = new GameSQLDAO();
    List<GameData> list = new ArrayList<>();
    ChessGame chessGame1 = new ChessGame();
    GameData gameData1 = new GameData(3, "josh", "sean", "dukgsalsud.hga", chessGame1);
    list = gameDAO.listGames();
    assertEquals(1, list.size());
    gameDAO.clearAll();
  }

  @Test
  public void testListGamesNoGames() throws DataAccessException, SQLException {
    GameDAO gameDAO = new GameSQLDAO();
    List<GameData> list = new ArrayList<>();
    list = gameDAO.listGames();
    assertEquals(0, list.size());
  }

}
