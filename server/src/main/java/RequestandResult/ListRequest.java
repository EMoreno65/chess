package RequestandResult;

import model.GameData;

import java.util.List;

public class ListRequest {
  public ListRequest(List<GameData> games, String authToken) {
    this.games=games;
    this.authToken=authToken;
  }

  public List<GameData> getGames() {
    return games;
  }

  public void setGames(List<GameData> games) {
    this.games=games;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  List<GameData> games;
  String authToken;

  public ListRequest(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage=errorMessage;
  }

  String errorMessage;
}
