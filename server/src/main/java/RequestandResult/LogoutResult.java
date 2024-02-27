package RequestandResult;

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

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  String authToken;
  String errorMessage;
  String username;
}
