package service;

import RequestandResult.JoinRequest;
import RequestandResult.JoinResult;
import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.GameData;

public class JoinService {
  public JoinResult joinResult(JoinRequest givenRequest, UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO){
    try{
      GameData givenGame = gameDAO.getGame(givenRequest.getGameID());
      if (givenGame.whiteUsername() == null)
    }
    catch(DataAccessException e){
      return new JoinResult(e.getMessage());
    }
    int i = 1;
    return null;
  }
}
