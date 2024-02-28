package service;

import RequestandResult.ListRequest;
import RequestandResult.ListResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.GameData;

import java.util.List;

public class ListService {

  public ListResult listResult(ListRequest givenRequest, AuthDAO authDAO, GameDAO gameDAO) {
    List<GameData> games = gameDAO.listGames();
    return new ListResult(games, null);
  }
}
