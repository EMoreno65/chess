package dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.ChessGame;
import model.GameData;

public class GameDAO {
  private static int nextGameID = 1;
  // Simulating a database storage for chess games
  public Map<Integer, GameData> gamesMap = new HashMap<>();

  public GameDAO() {
  }

  public void clearAll() {
    // Clear all chess games from the database
    gamesMap.clear();
  }

  // Method to create a new chess game
  public void createGame(int gameId, GameData game) throws DataAccessException {
    if (gamesMap.containsKey(gameId)) {
      throw new DataAccessException("Game already exists with ID: " + gameId);
    }
    gamesMap.put(gameId, game);
  }

  // Method to retrieve a chess game by its ID
  public GameData getGame(int gameId) throws DataAccessException {
    GameData game = gamesMap.get(gameId);
    if (game == null) {
      throw new DataAccessException("Game not found with ID: " + gameId);
    }
    return game;
  }

  public int getGameID(ChessGame gameToFind) throws DataAccessException {
    for (Map.Entry<Integer, GameData> entry : gamesMap.entrySet()) {
      if (entry.getValue().equals(gameToFind)) {
        return entry.getKey();
      }
    }
    throw new DataAccessException("Game not found with given ChessGame object");
  }


  // Method to retrieve all chess games
  public List<GameData> listGames() {
    return new ArrayList<>(gamesMap.values());
  }

  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException {
    if (!gamesMap.containsKey(gameId)) {
      throw new DataAccessException("Game not found with ID: " + gameId);
    }
    gamesMap.put(gameId, updatedGame);
  }

  public static synchronized int generateUniqueGameID() {
    return nextGameID++;
  }
}
