package server;

import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import service.RegisterService;

public class RegisterHandler {

  RegisterService myService = new RegisterService();

  public Object register(Request req, Response res) {
    String myJSON = req.body();
    RegisterRequest givenRequest = new Gson().fromJson(myJSON, RegisterRequest.class);
    RegisterResult myResult = myService.newResult(givenRequest);
    return new Gson().toJson(myResult);
  }
}
