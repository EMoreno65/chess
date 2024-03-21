package service;

import model.RequestandResult.ListResult;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.GameData;

import java.sql.SQLException;
import java.util.List;

public class ListService {

  public ListResult listResult(AuthDAO authDAO, GameDAO gameDAO) throws DataAccessException, SQLException {
    List<GameData> games = gameDAO.listGames();
    return new ListResult(games, null);
  }
}
