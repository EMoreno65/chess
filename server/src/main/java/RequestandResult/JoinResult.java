package RequestandResult;

import model.GameData;

public class JoinResult {
  public JoinResult(String authToken, GameData gameData) {
    this.authToken=authToken;
    this.gameData=gameData;
  }

  public JoinResult(String errorMessage){
    this.errorMessage = errorMessage;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  String authToken;

  public GameData getGameData() {
    return gameData;
  }

  GameData gameData;
  String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }
}
