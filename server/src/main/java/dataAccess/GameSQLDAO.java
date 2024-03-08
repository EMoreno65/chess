package dataAccess;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.GameData;

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

  public void clearAll() {
  }

  // Method to create a new chess game
  public void createGame(int gameId, GameData game) throws DataAccessException {
    String sql="INSERT INTO games (game_id, game_data) VALUES (?, ?)";

    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, gameId);
      preparedStatement.setObject(5, game);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error creating game");
    }
  }

  // Method to retrieve a chess game by its ID
  public GameData getGame(int gameId) throws DataAccessException {
    String sql="SELECT game_data FROM games WHERE game_id = ?";
    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, gameId);
      try (ResultSet resultSet=preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          String gameDataString=resultSet.getString("game_data");
          return deserializeGameData(gameDataString);
        } else {
          throw new DataAccessException("Game not found with ID: " + gameId);
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error retrieving game");
    }
  }

  private String serializeGameData(GameData gameData) {
    Gson gson=new Gson();
    return gson.toJson(gameData);
  }

  // Deserialization method
  private GameData deserializeGameData(String json) throws DataAccessException {
    Gson gson=new Gson();
    try {
      return gson.fromJson(json, GameData.class);
    } catch (JsonSyntaxException e) {
      throw new DataAccessException("Error deserializing game data");
    }
  }

  public int getGameID(ChessGame gameToFind) throws DataAccessException {
    String sql="SELECT game_id FROM game_data WHERE game = '?'";
    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {
      preparedStatement.setObject(5, gameToFind);
      try (ResultSet resultSet=preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getInt("game_id");
        }
      }
    } catch (SQLException ex) {
      throw new DataAccessException("Error finding user from authentication token");
    }
    return 0;
  }


  // Method to retrieve all chess games
  public List<GameData> listGames() throws DataAccessException, SQLException {
    List<GameData> gamesList=new ArrayList<>();
    String sql="SELECT * FROM games";

    Connection conn=DatabaseManager.getConnection();
    PreparedStatement preparedStatement=conn.prepareStatement(sql);
    ResultSet resultSet=preparedStatement.executeQuery();

    while (resultSet.next()) {
      String gameDataString=resultSet.getString("game_data");
      GameData gameData=deserializeGameData(gameDataString);
      gamesList.add(gameData);
    }

    return gamesList;
  }


  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException {
    String sql="UPDATE games SET game_data = ? WHERE game_id = ?";

    try (Connection conn=DatabaseManager.getConnection();
         PreparedStatement preparedStatement=conn.prepareStatement(sql)) {

      // Serialize the updatedGame object to JSON string
      String serializedGameData=serializeGameData(updatedGame);

      // Set parameters for the PreparedStatement
      preparedStatement.setString(1, serializedGameData);
      preparedStatement.setInt(2, gameId);

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
    String sql="SELECT MAX(game_id) AS max_id FROM games";

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
                whiteusername VARCHAR(100) DEFAULT NULL,
                blackusername VARCHAR(100) DEFAULT NULL,
                gamename VARCHAR(100) NOT NULL,
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
