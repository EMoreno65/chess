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
//                King logic
            case QUEEN:
                // Queen Logic
            case BISHOP:
                // Bishop Logic
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

            case KNIGHT:
                // Knight Logic
            case ROOK:
                // Rook logic
            case PAWN:
                // Pawn logic
        }
        return possibleMoves;
    }
}
