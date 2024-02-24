package dataAccess;

import chess.ChessGame;

public class GameDAO {

  public GameDAO(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {
    GameID=gameID;
    this.whiteUsername=whiteUsername;
    this.blackUsername=blackUsername;
    this.gameName=gameName;
    this.game=game;
  }

  public int getGameID() {
    return GameID;
  }

  public void setGameID(int gameID) {
    GameID=gameID;
  }

  public String getWhiteUsername() {
    return whiteUsername;
  }

  public void setWhiteUsername(String whiteUsername) {
    this.whiteUsername=whiteUsername;
  }

  public String getBlackUsername() {
    return blackUsername;
  }

  public void setBlackUsername(String blackUsername) {
    this.blackUsername=blackUsername;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName=gameName;
  }

  public ChessGame getGame() {
    return game;
  }

  public void setGame(ChessGame game) {
    this.game=game;
  }

  int GameID;
  String whiteUsername;
  String blackUsername;
  String gameName;
  ChessGame game;

}
