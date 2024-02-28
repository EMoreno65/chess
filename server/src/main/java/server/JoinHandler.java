package server;

import RequestandResult.JoinRequest;
import RequestandResult.JoinResult;
import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.results;
import service.JoinService;
import spark.Request;
import spark.Response;

import java.util.Objects;

public class JoinHandler {

  JoinService myService = new JoinService();
  public Object join(Request req, Response res, UserDAO userDAO, GameDAO gameDAO, AuthDAO authDAO) {
    String myJSON = req.body();
    JoinRequest givenRequest = new Gson().fromJson(myJSON, JoinRequest.class);
    givenRequest.setAuthtoken(req.headers("authorization"));
    Object myResult = myService.joinResult(givenRequest, userDAO, authDAO, gameDAO);
    if (myResult instanceof JoinResult) {
      JoinResult result = (JoinResult) myResult;
      String errorMessage = result.getErrorMessage();
      if (errorMessage != null) {
        if (Objects.equals(result.getErrorMessage(), "Game not found with ID: 0")){
          results resultMessage = new results("Error: bad request");
          res.status(400);
          return new Gson().toJson(resultMessage);
        }
        else if (Objects.equals(result.getErrorMessage(), "Error: unauthorized")){
          results resultMessage = new results("Error: unauthorized");
          res.status(401);
          return new Gson().toJson(resultMessage);
        }
        else if (Objects.equals(result.getErrorMessage(), "Error: already taken")){
          results resultMessage = new results("Error: already taken");
          res.status(403);
          return new Gson().toJson(resultMessage);
        }
      }
    }

    return new Gson().toJson(myResult);
  }
}
