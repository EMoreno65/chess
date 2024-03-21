package serverFacade;

// Make it easier for client / code is separated so UI doesn't have to consider HTTP
// Login, Register, Create, Join, etc
// They're gonna contact my server over http

import Request.*;
import com.google.gson.Gson;
import dataAccess.ResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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
  public RegisterRequest register(RegisterRequest request) throws ResponseException {
    var path = "/user";
    return this.makeRequest("POST", "/user", request, RegisterRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public LoginRequest login(LoginRequest request) throws ResponseException {
    var path = "/session";
    return this.makeRequest("POST", "/session", request, LoginRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public CreateRequest create(CreateRequest request) throws ResponseException {
    var path = "/game";
    return this.makeRequest("POST","/game", request, CreateRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public JoinRequest join(JoinRequest request) throws ResponseException{
    var path = "/game";
    return this.makeRequest("PUT", "/game", request, JoinRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public ListRequest list(ListRequest request) throws ResponseException{
    var path = "/game";
    return this.makeRequest("GET", "/game", request, ListRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public ClearRequest clear(ClearRequest request) throws ResponseException{
    var path = "/db";
    return this.makeRequest("DELETE", "/db", request, ClearRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }
  public LogoutRequest logout(LogoutRequest request) throws ResponseException{
    var path = "/session";
    return this.makeRequest("DELETE", "/session", request, LogoutRequest.class);
    // contact the server
    // Access request and execute request
    // Send request to server
  }

  private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws ResponseException {
    try {
      URL url = (new URI(serverUrl + path)).toURL();
      HttpURLConnection http = (HttpURLConnection) url.openConnection();
      http.setRequestMethod(method);
      http.setDoOutput(true);

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
