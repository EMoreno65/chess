package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    ChessBoard board;
    private TeamColor currentTurn;
    public ChessGame() {
        this.board = board;
        this.currentTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return currentTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.currentTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        HashSet<ChessMove> validMoves = new HashSet<>();
        ChessPosition kingPosition = findCurrentKing(board, startPosition);
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.KING){
            Collection<ChessMove> kingValidMoves = null;
            kingValidMoves = board.getPiece(startPosition).kingMoves(board, startPosition);
            for (ChessMove move: kingValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid moves
            }
            return kingValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.QUEEN){
            Collection<ChessMove> queenValidMoves = null;
            queenValidMoves.addAll(board.getPiece(startPosition).diagonalMoves(board, startPosition));
            queenValidMoves.addAll(board.getPiece(startPosition).straightMoves(board, startPosition));
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.BISHOP){
            // Bishop Logic
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.KNIGHT){
            // Knight Logic
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.ROOK){
            // Rook Logic
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.PAWN){
            // Pawn Logic
        }
        return validMoves;
    }

    public ChessPosition findCurrentKing(ChessBoard board, ChessPosition startPosition){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessPosition checkedPosition = new ChessPosition((i),(j));
                if (board.getPiece(checkedPosition) != null){
                    if (board.getPiece(checkedPosition).getPieceType() == ChessPiece.PieceType.KING){
                        if (board.getPiece(checkedPosition).getTeamColor() == board.getPiece(startPosition).getTeamColor()){
                            ChessPosition currentKingPosition = checkedPosition;
                            return currentKingPosition;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece movingPiece = board.getPiece(move.startPosition);
        board.addPiece(move.startPosition, null);
        board.addPiece(move.endPosition, movingPiece);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
