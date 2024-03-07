package server;

import RequestandResult.LoginResult;
import dataAccess.*;
import spark.*;

public class Server {

    public GameDAO newGame = null;
    public UserDAO newUser = null;
    public AuthDAO newAuth = null;

    // Do what I did in ClearService
    public Server(){
        try {
            newGame = new GameSQLDAO();
            newUser = new MemoryUserDAO();
            newAuth = new AuthSQLDAO();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
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
        Spark.get("/game", (req, res) -> (new ListHandler()).list(req, res, newAuth, newGame));
        Spark.post("/game", (req, res) -> (new CreateHandler()).create(req, res, newAuth, newGame, newUser));
        Spark.put("/game", (req, res) -> (new JoinHandler()).join(req, res, newUser, newGame, newAuth));

        //Register your endpoints and handle exceptions here.

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
