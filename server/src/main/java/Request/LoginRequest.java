package Request;

import model.AuthData;

public class LoginRequest {

  public LoginRequest(String username, String authToken, String password){
    this.username = username;
    this.authToken = authToken;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  String password;

}
