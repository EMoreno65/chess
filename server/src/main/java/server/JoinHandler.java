package server;

import RequestandResult.JoinRequest;
import RequestandResult.JoinResult;
import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import service.JoinService;
import spark.Request;
import spark.Response;

public class JoinHandler {

  JoinService myService = new JoinService();
  public Object join(Request req, Response res, UserDAO userDAO, GameDAO gameDAO, AuthDAO authDAO) {
    String myJSON = req.body();
    JoinRequest givenRequest = new Gson().fromJson(myJSON, JoinRequest.class);
    Object myResult = myService.joinResult(givenRequest, userDAO, authDAO, gameDAO);
    return new Gson().toJson(myResult);
  }
}
