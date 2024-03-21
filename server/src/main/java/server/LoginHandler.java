package server;

import model.Request.LoginRequest;
import model.RequestandResult.LoginResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.Results;
import service.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {

  LoginService myService = new LoginService();
  public Object login(Request req, Response res, UserDAO userDAO, AuthDAO authDAO) throws DataAccessException {
    String myJSON = req.body();
    LoginRequest givenRequest = new Gson().fromJson(myJSON, LoginRequest.class);
    LoginResult myResult = myService.newResult(givenRequest, userDAO, authDAO);
    // My result, if it has an error message, set message to error unauthorized, res.statuscode(401)
    if (myResult.getErrorMessage() != null){
      Results resultMessage = new Results("Error: unauthorized");
      res.status(401);
      return new Gson().toJson(resultMessage);
    }
      // String message = "Error: unauthorized"
    return new Gson().toJson(myResult);
  }
}
