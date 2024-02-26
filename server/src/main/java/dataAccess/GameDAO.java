package dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.ChessGame;

public class GameDAO {

  // Simulating a database storage for chess games
  public Map<Integer, ChessGame> gamesMap = new HashMap<>();

  public GameDAO() {
  }

  public void clearAll() {
    // Clear all chess games from the database
    gamesMap.clear();
  }

  // Method to create a new chess game
  public void createGame(int gameId, ChessGame game) throws DataAccessException {
    if (gamesMap.containsKey(gameId)) {
      throw new DataAccessException("Game already exists with ID: " + gameId);
    }
    gamesMap.put(gameId, game);
  }

  // Method to retrieve a chess game by its ID
  public ChessGame getGame(int gameId) throws DataAccessException {
    ChessGame game = gamesMap.get(gameId);
    if (game == null) {
      throw new DataAccessException("Game not found with ID: " + gameId);
    }
    return game;
  }

  // Method to retrieve all chess games
  public List<ChessGame> listGames() {
    return new ArrayList<>(gamesMap.values());
  }

  // Method to update a chess game
  public void updateGame(int gameId, ChessGame updatedGame) throws DataAccessException {
    if (!gamesMap.containsKey(gameId)) {
      throw new DataAccessException("Game not found with ID: " + gameId);
    }
    gamesMap.put(gameId, updatedGame);
  }

  // Method to delete a chess game
  public void deleteGame(int gameId) throws DataAccessException {
    if (!gamesMap.containsKey(gameId)) {
      throw new DataAccessException("Game not found with ID: " + gameId);
    }
    gamesMap.remove(gameId);
  }
}
