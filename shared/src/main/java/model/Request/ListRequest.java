package model.Request;

import model.GameData;

import java.util.List;

public class ListRequest {
  public ListRequest(List<GameData> games, String authToken) {
    this.games=games;
    this.authToken=authToken;
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

  String errorMessage;
}
