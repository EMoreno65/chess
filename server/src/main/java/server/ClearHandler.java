package server;

import RequestandResult.ClearRequest;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import spark.Request;
import spark.Response;
import service.ClearService;

public class ClearHandler {
  ClearService myService = new ClearService();
  public Object clear(Request req, Response res, UserDAO userDAO, GameDAO gameDAO, AuthDAO authDAO) {
    myService.clearAll(userDAO, authDAO, gameDAO);
    res.status(200); // Set HTTP status code to indicate success (e.g., 200 OK)
    return new Gson().toJson(myService);
  }
}
