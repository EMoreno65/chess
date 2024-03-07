package service;

import Request.CreateRequest;
import RequestandResult.CreateResult;
import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;

public class CreateService {
  public CreateResult createGame(CreateRequest givenRequest, AuthDAO authDAO, GameDAO gameDAO, UserDAO userDAO) throws DataAccessException {
    try {
      // Use authDAO to look up authToken and verify that it exists in the database
      AuthData authData = authDAO.getAuthData(givenRequest.getAuthToken());
      if (authData == null){
        DataAccessException e = new DataAccessException("Error: unauthorized");
        return new CreateResult(e.getMessage());
      }
      String gameName = givenRequest.getGameName();
      int gameId =gameDAO.findMaxID(); // Change to findMaxID
      GameData newGame = new GameData(gameId, null, null, gameName, new ChessGame());
      gameDAO.createGame(gameId, newGame);
      return new CreateResult(gameId);
    } catch (DataAccessException e) {
      return new CreateResult(e.getMessage());
    }
  }
}
