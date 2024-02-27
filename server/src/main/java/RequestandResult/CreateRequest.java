package RequestandResult;

import model.GameData;

public class CreateRequest {
  public CreateRequest(String authToken, GameData gameData){
    this.authToken = authToken;
    this.gameData = gameData;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  public GameData getGameData() {
    return gameData;
  }

  public void setGameData(GameData gameData) {
    this.gameData=gameData;
  }

  String authToken;
  GameData gameData;
}
