package server;

import RequestandResult.LoginResult;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import spark.*;

public class Server {

    public GameDAO newGame = new GameDAO();
    public UserDAO newUser = new UserDAO();
    public AuthDAO newAuth = new AuthDAO();
    public static void main(String[] args){
        new Server().run(8080);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        Spark.post("/user", (req, res) -> (new RegisterHandler()).register(req, res, newUser, newAuth));
        // Spark request object create it, take request and get needed info out of it - e.g. user data password etc
        Spark.delete("/db", (req, res) -> (new ClearHandler()).clear(req, res, newUser, newGame, newAuth));
        Spark.post("/session", (req, res) -> (new LoginHandler()).login(req, res, newUser, newAuth));
        Spark.delete("/session", (req, res) -> (new LogoutHandler()).logout(req, res, newAuth, newUser));
        Spark.get("/game", (req, res) -> (new ListHandler()).list(req, res));
        Spark.post("/game", (req, res) -> (new CreateHandler()).create(req, res));
        Spark.put("/game", (req, res) -> (new JoinHandler()).join(req, res));

        //Register your endpoints and handle exceptions here.

        Spark.awaitInitialization();
        int actualPort = 8080;
        return actualPort;
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
