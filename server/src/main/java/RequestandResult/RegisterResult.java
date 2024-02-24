package RequestandResult;
import model.AuthData;

public class RegisterResult {
  public RegisterResult() {
  }

  public AuthData getMyToken() {
    return myToken;
  }

  public void setMyToken(AuthData myToken) {
    this.myToken=myToken;
  }

  AuthData myToken;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  String errorMessage;



}
