package Request;

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

  public int getGameID() {
    return gameID;
  }

  String authtoken;
  ChessGame.TeamColor playerColor;
  int gameID;
}
