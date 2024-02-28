package server;

import RequestandResult.ListRequest;
import RequestandResult.ListResult;
import RequestandResult.LoginRequest;
import RequestandResult.LoginResult;
import com.google.gson.Gson;
import service.ListService;
import spark.Request;
import spark.Response;

public class ListHandler {
  ListService myService = new ListService();
  public Object list(Request req, Response res) {
    String myJSON = req.body();
    ListRequest givenRequest = new Gson().fromJson(myJSON, ListRequest.class);
    ListResult myResult = myService.listResult();
    return new Gson().toJson(myResult);
  }
}
