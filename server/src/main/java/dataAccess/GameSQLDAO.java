package dataAccess;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.GameData;
import model.UserData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSQLDAO implements GameDAO {
  private static int nextGameID=1;
  // Simulating a database storage for chess games
  public Map<Integer, GameData> gamesMap=new HashMap<>();
  // Go through the map and find the max integer in the map, then make a function that sets that equal to the (gameID + 1)

  public GameSQLDAO() throws DataAccessException {
    configureDatabase();
  }

  public void clearAll() throws DataAccessException{
    String sql = "DELETE FROM games";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error clearing game data");
    }
  }

  // Method to create a new chess game
//  public void createGame(int gameId, GameData game) throws DataAccessException {
//    String sql = "INSERT INTO games (gameID, game) VALUES (?, ?)";
//
//    try (Connection conn = DatabaseManager.getConnection();
//         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//      preparedStatement.setInt(1, gameId);
//      preparedStatement.setObject(2, game);
//      preparedStatement.executeUpdate();
//    } catch (SQLException ex) {
//      throw new DataAccessException("Error creating game");
//    }
//  }
  public void createGame(int gameId, GameData game) throws DataAccessException {
    String insertSql = "INSERT INTO games (gameID, whiteUsername, blackUsername, gameName, game) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
      int newGameID = findMaxID() + 1;
      preparedStatement.setInt(1, newGameID);
      preparedStatement.setObject(2, game.whiteUsername());
      preparedStatement.setObject(3, game.blackUsername());
      preparedStatement.setObject(4, game.gameName());
      preparedStatement.setObject(5, serializeGameData(new GameData(newGameID, game.whiteUsername(), game.blackUsername(), game.gameName(), game.game())));

      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error: " + ex.getMessage());
    }
  }



  private String serializeChessGame(ChessGame chessGame) {
    return new Gson().toJson(chessGame);
  }
  public GameData getGame(int gameId) throws DataAccessException {
    String sql="SELECT * FROM games WHERE gameID = ?";
    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, gameId);
      try (ResultSet resultSet=preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          int GameID = resultSet.getInt("gameID");
          String whiteUsernameString = resultSet.getString("whiteUsername");
          String blackUsernameString = resultSet.getString("blackUsername");
          String gameNameString = resultSet.getString("gameName");
          String chessGameString = resultSet.getString("game");
          ChessGame chessGame = deserializeChessGame(chessGameString);
          GameData newGameData = new GameData(GameID, whiteUsernameString, blackUsernameString, gameNameString, chessGame);
          return newGameData;
        } else {
          throw new DataAccessException("Game not found with ID: " + gameId);
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error retrieving game");
    }
  }

  private String serializeGameData(GameData gameData) {
    // Ensure whiteUsername and blackUsername are not null
    String whiteUsername = (gameData.whiteUsername() != null) ? gameData.whiteUsername() : "";
    String blackUsername = (gameData.blackUsername() != null) ? gameData.blackUsername() : "";

    // Create a new instance of GameData with updated whiteUsername and blackUsername
    gameData = new GameData(
            gameData.gameID(),
            whiteUsername,
            blackUsername,
            gameData.gameName(),
            gameData.game()
    );

    // Serialize the updated GameData object
    Gson gson = new Gson();
    return gson.toJson(gameData);
  }




  // Deserialization method
  private ChessGame deserializeChessGame(String json) throws DataAccessException {
    Gson gson=new Gson();
    try {
      return gson.fromJson(json, ChessGame.class);
    } catch (JsonSyntaxException e) {
      throw new DataAccessException("Error deserializing game data");
    }
  }

  public int getGameID(ChessGame gameToFind) throws DataAccessException {
    String sql="SELECT gameID FROM games WHERE game = ?";
    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setObject(1, gameToFind);
      try (ResultSet resultSet=preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getInt("gameID");
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error finding user from authentication token");
    }
    return 0;
  }

  public List<GameData> listGames() throws DataAccessException {
    List<GameData> gamesList = new ArrayList<>();
    String sql = "SELECT * FROM games";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

      while (resultSet.next()) {
        int GameID = resultSet.getInt("gameID");
        String whiteUsernameString = resultSet.getString("whiteUsername");
        String blackUsernameString = resultSet.getString("blackUsername");
        String gameNameString = resultSet.getString("gameName");
        String chessGameString = resultSet.getString("game");
        ChessGame chessGame = deserializeChessGame(chessGameString);
        GameData newGameData = new GameData(GameID, whiteUsernameString, blackUsernameString, gameNameString, chessGame);
        gamesList.add(newGameData);
      }

      // Check if no games were found
      if (gamesList.isEmpty()) {
        System.out.println("No games found in the database.");
      }

    } catch (SQLException ex) {
      throw new DataAccessException("Error retrieving games: " + ex.getMessage());
    }

    return gamesList;
  }


  // Method to retrieve all chess games



  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException {
    String sql="UPDATE games SET game = ?, whiteUsername = ?, blackUsername = ? WHERE gameID = ?";

    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {

      // Serialize the updatedGame object to JSON string
      String serializedGameData=serializeChessGame(updatedGame.game());

      // Set parameters for the PreparedStatement
      preparedStatement.setString(1, serializedGameData);
      preparedStatement.setString(2, updatedGame.whiteUsername());
      preparedStatement.setString(3, updatedGame.blackUsername());
      preparedStatement.setInt(4, gameId);

      // Execute the update query
      int rowsUpdated=preparedStatement.executeUpdate();

      // Check if any rows were affected
      if (rowsUpdated == 0) {
        throw new DataAccessException("Failed to update game with ID: " + gameId);
      }

    } catch (SQLException ex) {
      throw new DataAccessException("Error updating game");
    }
  }


  public int findMaxID() throws DataAccessException {
    String sql="SELECT MAX(gameID) AS max_id FROM games";

    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql);
         ResultSet resultSet=preparedStatement.executeQuery()) {

      if (resultSet.next()) {
        int maxID=resultSet.getInt("max_id");
        return maxID;
      } else {
        throw new DataAccessException("Failed to find maximum game ID");
      }

    } catch (SQLException ex) {
      throw new DataAccessException("Error finding maximum game ID");
    }
  }

  private final String[] createStatements={
          """
            CREATE TABLE IF NOT EXISTS games (
                gameID INT NOT NULL,
                whiteUsername VARCHAR(100),
                blackUsername VARCHAR(100),
                gameName VARCHAR(100) DEFAULT NULL,
                game TEXT DEFAULT NULL,
                PRIMARY KEY (gameID)
                
            );
            
            """
  };

  private void configureDatabase() throws DataAccessException {
    DatabaseManager.createDatabase();
    try (var conn = DatabaseManager.getConnection()) {
      for (var statement : createStatements) {
        try (var preparedStatement = conn.prepareStatement(statement)) {
          preparedStatement.executeUpdate();
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException(String.format("Unable to configure database: %s", ex.getMessage()));
    }
  }
}
