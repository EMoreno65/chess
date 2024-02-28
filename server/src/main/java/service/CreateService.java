package service;

import RequestandResult.CreateRequest;
import RequestandResult.CreateResult;
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
      String gameName = givenRequest.getGameName();
      GameData newGame = new GameData(0, null, null, null, null);
      int gameId = generateUniqueGameID();
      gameDAO.createGame(gameId, newGame);
      return new CreateResult(gameId);
    } catch (DataAccessException e) {
      return new CreateResult(e.getMessage());
    }
  }
}
