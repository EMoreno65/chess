package server;

import RequestandResult.LoginResult;
import RequestandResult.LogoutResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.Results;
import service.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
  LogoutService myService = new LogoutService();

  public Object logout(Request req, Response res, AuthDAO authDAO, UserDAO userDAO) throws DataAccessException {
    String authToken = req.headers("Authorization");
    LogoutResult myResult =(LogoutResult) myService.logout(authDAO, authToken);
    if (myResult != null && myResult.getErrorMessage() != null){
      Results resultMessage = new Results("Error: unauthorized");
      res.status(401);
      return new Gson().toJson(resultMessage);
    }
    res.status(200);
    return new Gson().toJson(myService);
  }
}

