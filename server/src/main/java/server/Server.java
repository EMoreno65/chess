package server;

import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        Spark.post("/user", (req, res) -> (new RegisterHandler()).register(req, res));
        Spark.delete("/db", (req, res) -> (new ClearHandler()).clear(req, res));
        Spark.post("/session", (req, res) -> (new LoginHandler()).login(req, res));
        Spark.delete("/session", (req, res) -> (new LogoutHandler()).logout(req, res));
        Spark.get("/game", (req, res) -> (new ListHandler()).list(req, res));
        Spark.post("/game", (req, res) -> (new CreateHandler()).create(req, res));
        Spark.put("/game", (req, res) -> (new JoinHandler()).join(req, res));

        //Register your endpoints and handle exceptions here.

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
