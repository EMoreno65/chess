package RequestandResult;

import chess.ChessGame;

public class JoinRequest {

  public JoinRequest(String authtoken, ChessGame.TeamColor playerColor, int gameID) {
    this.authtoken=authtoken;
    this.playerColor=playerColor;
    this.gameID=gameID;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }

  public ChessGame.TeamColor getPlayerColor() {
    return playerColor;
  }

  public void setPlayerColor(ChessGame.TeamColor color) {
    this.playerColor=color;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID=gameID;
  }

  String authtoken;
  ChessGame.TeamColor playerColor;
  int gameID;
}
