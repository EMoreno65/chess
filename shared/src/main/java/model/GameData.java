package model;

import chess.ChessGame;
import java.io.Serializable;

public record GameData(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {
  // Constructor with default values for whiteUsername and blackUsername
  public GameData(int gameID, String gameName, ChessGame game) {
    this(gameID, null, null, gameName, game);
  }
  public int gameID(){
    return gameID;
  }
  public String whiteUsername() {
      return whiteUsername;
    }

    public String blackUsername() {
      return blackUsername;
    }
    public String gameName(){
    return gameName;
    }
  }
