package server;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import service.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {

  LogoutService myService = new LogoutService();
  public Object logout(Request req, Response res, AuthDAO authDAO, UserDAO userDAO) throws DataAccessException {
    myService.logout(authDAO, userDAO);
    res.status(200);
    return "Logout Successful";
    //return null;
  }
}
