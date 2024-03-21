package model.RequestandResult;
import model.AuthData;

public class RegisterResult {
  public RegisterResult(String authToken, String username, String password) {
    this.authToken = authToken;
    this.username = username;
    this.password = password;
  }
  public RegisterResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  public String getUsername() {
    return username;
  }

  String authToken;
  String username;

  public String getPassword() {
    return password;
  }

  String password;

  public String getErrorMessage() {
    return errorMessage;
  }

  String errorMessage;

}
