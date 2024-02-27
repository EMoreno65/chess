package RequestandResult;

import chess.ChessGame;

public class JoinRequest {

  public JoinRequest(String authtoken, ChessGame.TeamColor color, int gameID) {
    this.authtoken=authtoken;
    this.color=color;
    this.gameID=gameID;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }

  public ChessGame.TeamColor getColor() {
    return color;
  }

  public void setColor(ChessGame.TeamColor color) {
    this.color=color;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID=gameID;
  }

  String authtoken;
  ChessGame.TeamColor color;
  int gameID;
}
