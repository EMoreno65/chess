package RequestandResult;

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

  public GameData getGameData() {
    return gameData;
  }

  public void setGameData(GameData gameData) {
    this.gameData=gameData;
  }

  String authToken;

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName=gameName;
  }

  String gameName;

  GameData gameData;

  public ChessGame getGame() {
    return game;
  }

  public void setGame(ChessGame game) {
    this.game=game;
  }

  ChessGame game;
}
