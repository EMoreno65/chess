package model.RequestandResult;

public class LoginResult {

  public LoginResult(String authToken, String username){
    this.authToken = authToken;
    this.username = username;
  }

  public LoginResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

  String authToken;

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getUsername() {
    return username;
  }

  String errorMessage;

  String username;

}
