package service;

import RequestandResult.CreateRequest;
import RequestandResult.CreateResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.AuthData;
import model.GameData;

public class CreateService {
  public CreateResult createGame(CreateRequest givenRequest, AuthDAO authDAO, GameDAO gameDAO) throws DataAccessException {
    GameData gameData = (gameDAO.getGame()).
  }
}
