package RequestandResult;
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

  public void setUsername(String username) {
    this.username=username;
  }

  String authToken;
  String username;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  String password;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  String errorMessage;

}
