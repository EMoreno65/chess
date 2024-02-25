package server;

import RequestandResult.ClearRequest;
import spark.Request;
import spark.Response;
import service.ClearService;

public class ClearHandler {

  ClearService myService = new ClearService();
  public Object clear(Request req, Response res) {
    myService.clearAll();
    return new ClearRequest();
  }
}
