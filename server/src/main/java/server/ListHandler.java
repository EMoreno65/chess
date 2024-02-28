package server;

import RequestandResult.ListRequest;
import RequestandResult.ListResult;
import RequestandResult.LoginRequest;
import RequestandResult.LoginResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.GameData;
import service.ListService;
import spark.Request;
import spark.Response;

public class ListHandler {
  ListService myService = new ListService();

  public Object list(Request req, Response res, AuthDAO authDAO, GameDAO gameDAO) {
    String myJSON = req.body();
    ListRequest givenRequest = new Gson().fromJson(myJSON, ListRequest.class);
    // givenRequest.setAuthToken(req.headers("authorization"));
    ListResult myResult = myService.listResult(givenRequest, authDAO, gameDAO);

    return new Gson().toJson(myResult);
  }
}
