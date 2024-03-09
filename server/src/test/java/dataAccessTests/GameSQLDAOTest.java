package dataAccessTests;

import chess.ChessGame;
import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ClearService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameSQLDAOTest {
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
    // Create a new ChessGame object
    ChessGame gameToFind = new ChessGame();
    GameDAO gameDAO = new GameSQLDAO();
    int expectedGameID = 1;
    gameDAO.createGame(expectedGameID, new GameData(expectedGameID, "white", "black", "name", gameToFind));

    // Retrieve the game ID of the inserted game
    int retrievedGameID = gameDAO.getGameID(gameToFind);

    // Assert that the retrieved game ID matches the expected game ID
    Assertions.assertEquals(expectedGameID, retrievedGameID, "Retrieved game ID should match the expected game ID");
  }


}
