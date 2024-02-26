package RequestandResult;

import model.AuthData;

public class LoginRequest {

  public LoginRequest(String username, String authToken){
    this.username = username;
    this.authToken = authToken;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  String username;
  String authToken;

}
