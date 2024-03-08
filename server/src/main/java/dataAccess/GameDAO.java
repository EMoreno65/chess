package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.ChessGame;
import model.GameData;

public interface GameDAO {
  public void clearAll() throws DataAccessException;

  // Method to create a new chess game
  public void createGame(int gameId, GameData game) throws DataAccessException;

  // Method to retrieve a chess game by its ID
  public GameData getGame(int gameId) throws DataAccessException;

  public int getGameID(ChessGame gameToFind) throws DataAccessException;


  // Method to retrieve all chess games
  public List<GameData> listGames() throws DataAccessException, SQLException;

  // Method to update a chess game
  public void updateGame(int gameId, GameData updatedGame) throws DataAccessException;

  public static int generateUniqueGameID(){
  return 0;
  }

  public int findMaxID() throws DataAccessException;
};
