package clientTests;

import chess.ChessGame;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.Request.JoinRequest;
import model.RequestandResult.*;
import model.UserData;
import org.junit.jupiter.api.*;
import server.Server;
import serverFacade.serverFacade;
import ui.ResponseException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ServerFacadeTests {

    private static Server server;
    public static serverFacade serverFacade = new serverFacade("http://localhost:8080");

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @BeforeEach
    public void clearEverything() throws ResponseException {
        serverFacade.clear();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void registerSuccess() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        RegisterResult newResult = serverFacade.register(userData);
        String name = newResult.getUsername();
        assertEquals(name, username);
    }
    @Test
    public void registerFail() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);

        try {
            RegisterResult newResult = serverFacade.register(userData);
        } catch (ResponseException e) {
            assertEquals(500, e.StatusCode(), "Expected status code 500");

            // Check if error message is present
            assertNotNull(e.getMessage(), "Error message should be present");
        }
    }
    @Test
    public void loginRequest() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult result = serverFacade.login(userData);
        assertNotNull(result.getAuthToken());
    }

    @Test
    public void loginFail() throws ResponseException {
        try {
            String username="Ethan";
            UserData userData=new UserData("Ethan", "Password", "email");
            serverFacade.register(userData);
            UserData falseUserData=new UserData("Ethan", "sihdfluigewlhguqre", "email");
            LoginResult result=serverFacade.login(falseUserData);
            assertNull(result.getAuthToken());
        }
        catch (ResponseException e){
            assertEquals(500, e.StatusCode(), "Expected status code 500");
        }
    }

    @Test
    public void createSuccess() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult logResult = serverFacade.login(userData);
        String authToken = logResult.getAuthToken();
        String gameName = "someName";
        String requestBodyString = new Gson().toJson(gameName);
        CreateResult createResult = serverFacade.create(requestBodyString, authToken);
        assertNotNull(createResult.getGameID());
    }

    @Test
    public void createFail() throws ResponseException {
        try {
            String username="Ethan";
            UserData userData=new UserData("Ethan", "Password", "email");
            serverFacade.register(userData);
            LoginResult logResult=serverFacade.login(userData);
            String authToken="badAuthToken";
            String gameName="someName";
            String requestBodyString=new Gson().toJson(gameName);
            CreateResult createResult=serverFacade.create(requestBodyString, authToken);
            assertNotNull(createResult.getGameID());
        }
        catch(ResponseException e){
            assertEquals(500, e.StatusCode(), "Expected status code 500");
        }
    }

    @Test
    public void logoutSuccess() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult logResult = serverFacade.login(userData);
        String authToken = logResult.getAuthToken();
        AuthData authData = new AuthData(authToken, username, userData.getPassword());
        LogoutResult result = serverFacade.logout(authData);
        assertNull(result.getAuthToken());
    }

    @Test
    public void logoutFail() throws ResponseException {
        try {
            String username="Ethan";
            UserData userData=new UserData("Ethan", "Password", "email");
            serverFacade.register(userData);
            LoginResult logResult=serverFacade.login(userData);
            String authToken=logResult.getAuthToken();
            AuthData authData=new AuthData("dkusbg;luab", username, userData.getPassword());
            LogoutResult result=serverFacade.logout(authData);
        }
        catch (ResponseException e){
            assertEquals(500, e.StatusCode(), "Expected status code 500");
        }
    }

    @Test
    public void joinSuccess() throws ResponseException {
        String username="Ethan";
        UserData userData=new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult logResult=serverFacade.login(userData);
        String authToken=logResult.getAuthToken();
        CreateResult result = serverFacade.create("GameExample", authToken);
        JoinRequest request = new JoinRequest(authToken, ChessGame.TeamColor.WHITE, result.getGameID());
        JoinResult newResult =serverFacade.join(authToken, ChessGame.TeamColor.WHITE, result.getGameID());
        assertEquals(username, (newResult.getGameData().whiteUsername()));
    }

    @Test
    public void joinFail() throws ResponseException {
        String username="Ethan";
        UserData userData=new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult logResult=serverFacade.login(userData);
        String authToken=logResult.getAuthToken();
        CreateResult result=null;

        assertThrows(NullPointerException.class, () -> {
            JoinRequest newRequest =new JoinRequest(authToken, ChessGame.TeamColor.WHITE, result.getGameID()); // This line should throw a NullPointerException
        });
    }

    @Test
    public void listSuccess() throws ResponseException {
        String username = "Ethan";
        UserData userData = new UserData("Ethan", "Password", "email");
        serverFacade.register(userData);
        LoginResult logResult = serverFacade.login(userData);
        String authToken = logResult.getAuthToken();

        CreateResult createResult1 = serverFacade.create("GameExample", authToken);
        CreateResult createResult2 = serverFacade.create("GameExample2", authToken);

        List<GameData> gameDataList = new ArrayList<>();
        gameDataList.add(new GameData(createResult1.getGameID(), null, null, "GameExample", null));
        gameDataList.add(new GameData(createResult2.getGameID(), null, null, "GameExample2", null));

        ListResult listResult = serverFacade.list(gameDataList, authToken);

        assertEquals(2, listResult.getGames().size());
    }



}
