package service;

import RequestandResult.JoinRequest;
import RequestandResult.JoinResult;
import RequestandResult.LoginResult;
import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.GameData;
import model.UserData;

public class JoinService {
  public Object joinResult(JoinRequest givenRequest, UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO){
    try{
      GameData givenGame = gameDAO.getGame(givenRequest.getGameID());
      if (givenGame == null) {
        DataAccessException e = new DataAccessException("Error: bad request");
        return new JoinResult(e.getMessage());
      }
      if (givenRequest.getPlayerColor() == null){
        return "";
      }
      // Check if name is null and throw an excpetion
      if (givenRequest.getPlayerColor() != null){
        String username = authDAO.findUserFromAuthToken(givenRequest.getAuthtoken());
        UserData user = userDAO.getUser(username);
        if (givenRequest.getPlayerColor() == ChessGame.TeamColor.WHITE){
          GameData newGameData = new GameData(givenGame.GameID(), givenGame.whiteUsername(), givenGame.blackUsername(), givenGame.gameName(), givenGame.game());
          new GameData(givenGame.GameID(), user.getUsername(), givenGame.blackUsername(), givenGame.gameName(), givenGame.game());
          return newGameData;
        }
        else if (givenRequest.getPlayerColor() == ChessGame.TeamColor.BLACK){
          GameData newGameData = new GameData(givenGame.GameID(), givenGame.whiteUsername(), givenGame.blackUsername(), givenGame.gameName(), givenGame.game());
          new GameData(givenGame.GameID(), givenGame.whiteUsername(), user.getUsername(), givenGame.gameName(), givenGame.game());
          return newGameData;
        }
        // Gamedata at black or white username, make new gamedata, set username part of gameData to user's username
      }
      // I want to make it so the player is attached to the color, otherwise they join as a spectator
    }
    catch(DataAccessException e){
      return new JoinResult(e.getMessage());
    }
    return null;
  }
}
