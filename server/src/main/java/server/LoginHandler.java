package server;

import RequestandResult.LoginRequest;
import RequestandResult.LoginResult;
import RequestandResult.RegisterRequest;
import com.google.gson.Gson;
import dataAccess.UserDAO;
import service.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {

  LoginService myService = new LoginService();
  public Object login(Request req, Response res, UserDAO userDAO) {
    String myJSON =req.body();
    LoginRequest givenRequest = new Gson().fromJson(myJSON, LoginRequest.class);
    LoginResult myResult = myService.newResult()
  }
}
