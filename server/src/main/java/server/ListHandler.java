package server;

import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.RequestandResult.ListResult;
import model.Results;
import service.ListService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class ListHandler {
  ListService myService = new ListService();

  public Object list(Request req, Response res, AuthDAO authDAO, GameDAO gameDAO) throws DataAccessException, SQLException {
    String token = req.headers("Authorization");
    if (authDAO.isValidToken(token)){
      ListResult myResult = myService.listResult(authDAO, gameDAO);
      return new Gson().toJson(myResult);

    }
    Results resultMessage = new Results("Error: unauthorized");
    res.status(401);
    return new Gson().toJson(resultMessage);
  }
}
