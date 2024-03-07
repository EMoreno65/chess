package dataAccess;

import chess.ChessGame;
import model.GameData;

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
  }

  // Method to retrieve a chess game by its ID
  public GameData getGame(int gameId) throws DataAccessException {
    return null;
  }

  public int getGameID(ChessGame gameToFind) throws DataAccessException {
    return 0;
  }


  // Method to retrieve all chess games
  public List<GameData> listGames() {
    return null;
  }

  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException {
  }

  public int findMaxID() {
    return 0;
  }

}
