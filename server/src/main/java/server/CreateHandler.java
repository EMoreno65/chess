package server;

import RequestandResult.CreateRequest;
import RequestandResult.CreateResult;
import RequestandResult.LoginRequest;
import RequestandResult.LoginResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import service.CreateService;
import spark.Request;
import spark.Response;

public class CreateHandler {

  CreateService myService = new CreateService();
  public Object create(Request req, Response res, AuthDAO authDAO, GameDAO gameDAO) throws DataAccessException {
    String myJSON = req.body();
    CreateRequest givenRequest = new Gson().fromJson(myJSON, CreateRequest.class);
    CreateResult myResult = myService.createGame(givenRequest, authDAO, gameDAO);
  }
}
