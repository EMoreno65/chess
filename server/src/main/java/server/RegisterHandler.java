package server;

import model.Request.RegisterRequest;
import model.RequestandResult.RegisterResult;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import model.Results;
import spark.Request;
import spark.Response;
import service.RegisterService;

public class RegisterHandler {

  RegisterService myService = new RegisterService();

  public Object register(Request req, Response res, UserDAO userDAO, AuthDAO authDAO) {
    String myJSON = req.body();
    RegisterRequest givenRequest = new Gson().fromJson(myJSON, RegisterRequest.class);
    RegisterResult myResult = myService.newResult(givenRequest, userDAO, authDAO);
    if (myResult.getErrorMessage() != null){
      if (myResult.getErrorMessage().equals("Error: already taken")){
        Results resultMessage = new Results(myResult.getErrorMessage());
        res.status(403);
        return new Gson().toJson(resultMessage);
      }
      Results resultMessage = new Results("Error: unauthorized");
      res.status(400);
      return new Gson().toJson(resultMessage);
    }
    return new Gson().toJson(myResult);
  }
}
