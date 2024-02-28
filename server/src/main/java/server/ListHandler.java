package server;

import RequestandResult.*;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.GameData;
import model.results;
import service.ListService;
import spark.Request;
import spark.Response;

public class ListHandler {
  ListService myService = new ListService();

  public Object list(Request req, Response res, AuthDAO authDAO, GameDAO gameDAO) throws DataAccessException {
    String token = req.headers("Authorization");
    if (authDAO.isValidToken(token)){
      ListResult myResult = myService.listResult(authDAO, gameDAO);
      return new Gson().toJson(myResult);
    }
    results resultMessage = new results("Error: unauthorized");
    res.status(401);
    return new Gson().toJson(resultMessage);
  }
}
