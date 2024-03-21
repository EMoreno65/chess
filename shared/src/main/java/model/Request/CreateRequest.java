package model.Request;

import chess.ChessGame;
import model.GameData;

public class CreateRequest {
  public CreateRequest(String authToken, String gameName, GameData gameData, ChessGame game){
    this.authToken = authToken;
    this.gameName = gameName;
    this.gameData = gameData;
    this.game = game;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  String authToken;

  public String getGameName() {
    return gameName;
  }

  String gameName;

  GameData gameData;

  public ChessGame getGame() {
    return game;
  }

  ChessGame game;
}
