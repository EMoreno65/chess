package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

import java.sql.SQLOutput;

public class printObject {
  // Write code to set text and print it
  // Loop through every piece on the board
  public static void main(String[] args) {
    System.out.print(EscapeSequences.SET_BG_COLOR_YELLOW);
    System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
    System.out.print("Hello World");
    System.out.println(EscapeSequences.RESET_BG_COLOR);
    System.out.print("Hello World");
  }

  // Have a new method that takes in a chess board/game and print it out based on the position

  public static void drawWhiteBoard(ChessGame game){
    for (int i = 1; i <= 8; i++){ // Iterate through each square in board
      for (int j = 1; j <= 8; j++){

      }
    }
  }
  public String findPieceLetter(ChessBoard board, ChessPosition position){
    ChessPiece givenPiece = board.getPiece(position);
    if (givenPiece.getTeamColor() == ChessGame.TeamColor.WHITE){
      if (givenPiece.getPieceType() == ChessPiece.PieceType.PAWN){

      }
    }
  }
}
