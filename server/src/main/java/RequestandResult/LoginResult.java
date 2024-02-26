package RequestandResult;

public class LoginResult {

  public LoginResult(String authToken, String username){
    this.authToken = authToken;
    this.username = username;
  }

  public LoginResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

  String authToken;
  String errorMessage;

  String username;

}
