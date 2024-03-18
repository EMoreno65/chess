package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class printObject {
  // Write code to set text and print it
  // Loop through every piece on the board
  public static void main(String[] args) {
    ChessGame game = new ChessGame();
    ChessBoard board = game.getBoard();
    board.resetBoard();
    printObject.drawWhiteBoard(game);
  }

  // Have a new method that takes in a chess board/game and print it out based on the position

  public static void drawWhiteBoard(ChessGame game) {
    ChessBoard board=game.getBoard();
    for (int i=0; i <= 9; i++) { // Rows
      for (int j=0; j <= 9; j++) { // Columns
        if (i == 0 || i == 9 || j == 0 || j == 9){
          System.out.print(EscapeSequences.SET_BG_COLOR_DARK_GREY);
          System.out.print(EscapeSequences.RESET_TEXT_COLOR);
          String borderChar = findBorderLetterOrNumber(i,j);
          if (Character.isLetter(borderChar.charAt(0))) {
            System.out.print("  " + borderChar);
          } else if (Character.isDigit(borderChar.charAt(0))) {
            System.out.print(" " + borderChar);
          }
        }
        else {
          ChessPosition position=new ChessPosition(i, j);
          ChessPiece piece=board.getPiece(position);
          if (piece != null) {
            String pieceLetter=findPieceLetter(board, position);
            if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
              System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
            } else {
              System.out.print(EscapeSequences.SET_BG_COLOR_BLACK);
            }
            System.out.print(pieceLetter);
          } else {
            if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
              System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
            } else {
              System.out.print(EscapeSequences.SET_BG_COLOR_BLACK);
            }
            if (i == 8) {
              System.out.print("   ");
            } else {
              System.out.print("   ");
            }
          }
        }
      }
      System.out.print(EscapeSequences.RESET_BG_COLOR);
      System.out.println();
    }
    System.out.println();
  }

  public static void drawBlackBoard(ChessGame game){
    ChessBoard board=game.getBoard();
    for (int i=8; i >= 1; i--) { // Rows
      for (int j=8; j >= 1; j--) { // Columns
        ChessPosition position=new ChessPosition(i, j);
        ChessPiece piece=board.getPiece(position);
        if (piece != null) {
          String pieceLetter=findPieceLetter(board, position);
          if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
            System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
          } else {
            System.out.print(EscapeSequences.SET_BG_COLOR_BLACK);
          }
          System.out.print(pieceLetter);
        } else {
          if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) {
            System.out.print(EscapeSequences.SET_BG_COLOR_WHITE);
          } else {
            System.out.print(EscapeSequences.SET_BG_COLOR_BLACK);
          }
          if (i == 8) {
            System.out.print("   ");
          } else {
            System.out.print("   ");
          }
        }
      }
      System.out.print(EscapeSequences.RESET_BG_COLOR);
      System.out.println();
    }
    System.out.println();
  }

  public static String findPieceLetter(ChessBoard board, ChessPosition position){
    ChessPiece givenPiece = board.getPiece(position);
    if (givenPiece.getTeamColor() == ChessGame.TeamColor.WHITE){
      if (givenPiece.getPieceType() == ChessPiece.PieceType.PAWN){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " P ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.ROOK){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " R ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.KNIGHT){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " N ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.BISHOP){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " B ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.QUEEN){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " Q ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.KING){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
        return " K ";
      }
    }
    if (givenPiece.getTeamColor() == ChessGame.TeamColor.BLACK){
      if (givenPiece.getPieceType() == ChessPiece.PieceType.PAWN){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " P ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.ROOK){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " R ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.KNIGHT){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " N ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.BISHOP){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " B ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.QUEEN){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " Q ";
      }
      if (givenPiece.getPieceType() == ChessPiece.PieceType.KING){
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
        return " K ";
      }
    }
    return "";
  }
  public static String findBorderLetterOrNumber(int row, int col) {
    if ((row == 0 || row == 9) && col >= 1 && col < 9) {
      char letter = (char) ('a' + col - 1);
      if (col == 9) {
        return "   ";
      }
      return String.valueOf(letter);
    }
    if (col == 0 && row >= 1 && row < 9) {
      char number = (char) ('1' + row - 1);
      if (row == 9){
        return "   ";
      }
      return String.valueOf(number);
    }
    return "";
  }
}
