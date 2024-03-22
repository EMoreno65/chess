package serverFacade;

// Make it easier for client / code is separated so UI doesn't have to consider HTTP
// Login, Register, Create, Join, etc
// They're gonna contact my server over http

import chess.ChessGame;
import com.google.gson.Gson;
import model.RequestandResult.*;
import ui.ResponseException;
import model.*;
import model.Request.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class serverFacade {
  private String serverUrl;

  public void ServerFacade(String url) {
    serverUrl = url;
  }

  public serverFacade(String serverUrl) {
    this.serverUrl=serverUrl;
  }

  // User host and port to contact server
  // Move server request folders to shared  java
  public RegisterResult register(UserData data) throws ResponseException {
    var path = "/user";
    return this.makeRequest("POST", "/user", data, "", RegisterResult.class, null, null);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public LoginResult login(UserData data) throws ResponseException {
    var path = "/session";
    return this.makeRequest("POST", "/session", data, "", LoginResult.class, null, null);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public CreateResult create(String gameName, String authToken) throws ResponseException {
    var path = "/game";
    CreateRequest createRequest = new CreateRequest(authToken, gameName, null, null);
    return this.makeRequest("POST","/game", createRequest, authToken, CreateResult.class, "Authorization", authToken);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public JoinResult join(String authToken, ChessGame.TeamColor playerColor, int gameID) throws ResponseException{
    var path = "/game";
    JoinRequest joinRequest = new JoinRequest(authToken, playerColor, gameID);
    return this.makeRequest("PUT", "/game", joinRequest, "", JoinResult.class, "Authorization", authToken);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public ListResult list(List<GameData> games, String authToken) throws ResponseException{
    return this.makeRequest("GET", "/game", null, "", ListResult.class, "Authorization", authToken);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public ClearResult clear() throws ResponseException{
    return this.makeRequest("DELETE", "/db", null, null, null, null, null);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public LogoutResult logout(AuthData data) throws ResponseException{
    var path = "/session";
    return this.makeRequest("DELETE", "/session", data, data.authToken(), LogoutResult.class, "Authorization", data.authToken());
    // contact the server
    // Access request and execute request
    // Send request to server
  }

  private <T> T makeRequest(String method, String path, Object request, String authToken, Class<T> responseClass, String headerName, String headerValue) throws ResponseException {
    try {
      URL url = (new URI(serverUrl + path)).toURL();
      HttpURLConnection http = (HttpURLConnection) url.openConnection();
      http.setRequestMethod(method);
      http.setRequestProperty("authorization", authToken);
      http.setDoOutput(true);

      if (headerName != null && headerValue != null){
        http.setRequestProperty(headerName, headerValue);
      }

      writeBody(request, http);
      http.connect();
      throwIfNotSuccessful(http);
      return readBody(http, responseClass);
    } catch (Exception ex) {
      throw new ResponseException(500, ex.getMessage());
    }
  }

  private static void writeBody(Object request, HttpURLConnection http) throws IOException {
    if (request != null) {
      http.addRequestProperty("Content-Type", "application/json");
      String reqData = new Gson().toJson(request);
      try (OutputStream reqBody = http.getOutputStream()) {
        reqBody.write(reqData.getBytes());
      }
    }
  }
  private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
    T response = null;
    if (http.getContentLength() < 0) {
      try (InputStream respBody = http.getInputStream()) {
        InputStreamReader reader = new InputStreamReader(respBody);
        if (responseClass != null) {
          response = new Gson().fromJson(reader, responseClass);
        }
      }
    }
    return response;
  }

  private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
    var status = http.getResponseCode();
    if (!isSuccessful(status)) {
      throw new ResponseException(status, "failure: " + status);
    }
  }

  private boolean isSuccessful(int status) {
    return status / 100 == 2;
  }

}
