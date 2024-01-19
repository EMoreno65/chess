package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private PieceType type;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        switch(getPieceType()){
            case KING:
                return kingMoves(board, myPosition);
            case QUEEN:
                possibleMoves.addAll(diagonalMoves(board, myPosition));
                possibleMoves.addAll(straightMoves(board, myPosition));
                break;
            case BISHOP:
                return diagonalMoves(board, myPosition);
            case KNIGHT:
                return knightMoves(board, myPosition);
            case ROOK:
                return straightMoves(board, myPosition);
            case PAWN:
                return pawnMoves(board, myPosition);
        }
        return possibleMoves;
    }
    public Collection<ChessMove> diagonalMoves(ChessBoard board, ChessPosition myPosition){
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i++, j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j >= 1; i++, j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j >= 1; i--, j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j <= 8; i--, j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        return possibleMoves;
    }
    public Collection<ChessMove> straightMoves(ChessBoard board, ChessPosition myPosition){
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1; i--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); j <= 8; j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); j >= 1; j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        return possibleMoves;
}
    public Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition){
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1; i--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); j <= 8; j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); j >= 1; j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i++, j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j >= 1; i++, j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j >= 1; i--, j--) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j <= 8; i--, j++) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break; // Go to the next for loop
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        return possibleMoves;
    }
    public Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition){
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i+= 2, j+= 1) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j >= 1; i+= 2, j-= 1) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j <= 8; i-= 1, j+= 2) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j >= 1; i-= 2, j-= 1) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j <= 8; i-= 2, j+= 1) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i >= 1 && j >= 1; i-= 1, j-= 2) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j <= 8; i+= 1, j+= 2) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        for (int i = myPosition.getRow(), j = myPosition.getColumn(); i <= 8 && j >= 1; i+= 1, j-= 2) {
            if (i == myPosition.getRow() && j == myPosition.getColumn()){
                continue;
            }
            ChessPosition currentPosition = new ChessPosition(i,j);
            ChessPiece pieceAtPosition = board.getPiece(currentPosition);
            if (pieceAtPosition != null){
                if (pieceAtPosition.pieceColor == this.pieceColor){
                    break;
                }
                else{
                    ChessPosition newPosition = new ChessPosition(i,j);
                    ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                    possibleMoves.add(addedMove);
                    break;
                }
            }
            ChessPosition newPosition = new ChessPosition(i,j);
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
            break;
        }
        return possibleMoves;
    }
    public Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition){
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        if (this.pieceColor == ChessGame.TeamColor.WHITE && myPosition.getRow() < 8){
            if (myPosition.getRow() == 2){
                ChessPosition newPosition = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                possibleMoves.add(addedMove);
            }
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            if (newPosition.getRow() == 8){
                // Right here, I wanna find a way to find what the promoted piece is so I can return an object with that piece
                // Do I just need to initialize 4 separate objects all with the same position parameters and add that to possible moves?
                ChessMove addedMove = new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                possibleMoves.add(addedMove);
                ChessMove addedMove2 = new ChessMove(myPosition, newPosition, PieceType.ROOK);
                possibleMoves.add(addedMove2);
                ChessMove addedMove3 = new ChessMove(myPosition, newPosition, PieceType.QUEEN);
                possibleMoves.add(addedMove3);
                ChessMove addedMove4 = new ChessMove(myPosition, newPosition, PieceType.KNIGHT);
                possibleMoves.add(addedMove4);
            }
            else{
                ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                possibleMoves.add(addedMove);
            }
        }
        if (this.pieceColor == ChessGame.TeamColor.BLACK && myPosition.getRow() > 1){
            if (myPosition.getRow() == 7){
                ChessPosition newPosition = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
                possibleMoves.add(addedMove);
            }
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            ChessMove addedMove = new ChessMove(myPosition, newPosition, null);
            possibleMoves.add(addedMove);
        }
        return possibleMoves;
}}
