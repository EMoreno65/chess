package serverFacade;

// Make it easier for client / code is separated so UI doesn't have to consider HTTP
// Login, Register, Create, Join, etc
// They're gonna contact my server over http

import com.google.gson.Gson;
import dataAccess.ResponseException;

import java.io.IOException;
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
  public void register(){
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

}
