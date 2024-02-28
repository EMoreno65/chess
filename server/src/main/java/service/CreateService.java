package service;

import RequestandResult.CreateRequest;
import RequestandResult.CreateResult;
import RequestandResult.LoginResult;
import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;

import static dataAccess.GameDAO.generateUniqueGameID;

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
      GameData newGame = new GameData(0, null, null, null, null);
      int gameId = generateUniqueGameID();
      gameDAO.createGame(newGame);
      return new CreateResult(gameId);
    } catch (DataAccessException e) {
      return new CreateResult(e.getMessage());
    }
  }
}
