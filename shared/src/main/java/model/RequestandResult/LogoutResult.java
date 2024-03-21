package model.RequestandResult;

public class LogoutResult {
  public LogoutResult(String authToken, String username){
    this.authToken = authToken;
    this.username = username;
  }

  public LogoutResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

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

  String authToken;
  String errorMessage;
  String username;
}
