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

public class GameSQLDAO implements GameDAO{
  private static int nextGameID = 1;
  // Simulating a database storage for chess games
  public Map<Integer, GameData> gamesMap = new HashMap<>();
  // Go through the map and find the max integer in the map, then make a function that sets that equal to the (gameID + 1)

  public void clearAll() {
  }

  // Method to create a new chess game
  public void createGame(int gameId, GameData game) throws DataAccessException {
    String sql = "INSERT INTO games (game_id, game_data) VALUES (?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, gameId);
      preparedStatement.setObject(5,game);
      preparedStatement.executeUpdate();
    } catch (SQLException ex) {
      throw new DataAccessException("Error creating game");
    }
  }

  // Method to retrieve a chess game by its ID
  public GameData getGame(int gameId) throws DataAccessException {
    String sql = "SELECT game_data FROM games WHERE game_id = ?";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, gameId);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          String gameDataString = resultSet.getString("game_data");
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
    Gson gson = new Gson();
    return gson.toJson(gameData);
  }

  // Deserialization method
  private GameData deserializeGameData(String json) throws DataAccessException {
    Gson gson = new Gson();
    try {
      return gson.fromJson(json, GameData.class);
    } catch (JsonSyntaxException e) {
      throw new DataAccessException("Error deserializing game data");
    }
  }

  public int getGameID(ChessGame gameToFind) throws DataAccessException {
    String sql = "SELECT game_id FROM game_data WHERE game = '?'";
    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setObject(5, gameToFind);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
  public List<GameData> listGames() {
    String sql = "SELECT * FROM games";

  }

  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException {
  }

  public int findMaxID() {
    return 0;
  }

}
