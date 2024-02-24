package model;

import chess.ChessGame;

public record GameData(int GameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {

    public GameData(int GameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {
      this.GameID = GameID;
      this.whiteUsername = whiteUsername;
      this.blackUsername = blackUsername;
      this.gameName = gameName;
      this.game = game;

      }
      public int getGameID() {
        return GameID;
      }

      public String getwhiteUsername() {
        return whiteUsername;
      }

      public String getblackUsername() {
        return blackUsername;
      }

    public String getGameName() {
      return gameName;
    }
    public ChessGame getGame() {
      return game;
    }
}
