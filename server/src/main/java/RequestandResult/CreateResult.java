package RequestandResult;

public class CreateResult {
  public CreateResult(int gameID){
    this.gameID = gameID;
  }

  public CreateResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID=gameID;
  }

  int gameID;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  String errorMessage;
}
