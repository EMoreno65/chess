package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGameDAO implements GameDAO{
    private static int nextGameID = 1;
    // Simulating a database storage for chess games
    public Map<Integer, GameData> gamesMap = new HashMap<>();
    // Go through the map and find the max integer in the map, then make a function that sets that equal to the (gameID + 1)

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

  @Override
  public int findMaxID() {
      gamesMap.keySet(// find maximum number);
              // return said number
              // Get rid of generateUniqueGameID and replace with findMaxID
    return 0;
  }

}
