package server;

import RequestandResult.RegisterRequest;
import RequestandResult.RegisterResult;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RegisterHandler {

  server.RegisterService myService = new server.RegisterService();

  public Object register(Request req, Response res) {
    String myJSON = req.body();
    RegisterRequest givenRequest = new Gson().fromJson(myJSON, RegisterRequest.class);
    RegisterResult myResult = myService.newResult(givenRequest);
    return new Gson().toJson(myResult);
  }
}
