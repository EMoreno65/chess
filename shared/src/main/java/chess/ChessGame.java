package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "ChessGame{" +
                "board=" + board +
                ", currentTurn=" + currentTurn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame=(ChessGame) o;
        return Objects.equals(board, chessGame.board) && currentTurn == chessGame.currentTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, currentTurn);
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
        ChessPosition kingPosition = findCurrentKing(board, board.getPiece(startPosition).getTeamColor());
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

    public ChessPosition findCurrentKing(ChessBoard board, TeamColor TeamColor){
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                ChessPosition checkedPosition = new ChessPosition((i),(j));
                if (board.getPiece(checkedPosition) != null){
                    if (board.getPiece(checkedPosition).getPieceType() == ChessPiece.PieceType.KING){
                        if (board.getPiece(checkedPosition).getTeamColor() == TeamColor){
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
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++) {
                ChessPosition checkedPiecePosition=new ChessPosition((i), (j));
                if (board.getPiece(checkedPiecePosition) != null ){
                    if (board.getPiece(checkedPiecePosition).getTeamColor() != teamColor){
                        ChessPiece givenPiece = board.getPiece(checkedPiecePosition);
                        Collection<ChessMove> givenPiecePossibleMoves = board.getPiece(checkedPiecePosition).pieceMoves(board, checkedPiecePosition);
                        for (ChessMove move: givenPiecePossibleMoves){
                            ChessPosition kingPosition = findCurrentKing(board, teamColor);
                            if (move.endPosition.equals(kingPosition)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) throws InvalidMoveException {
        // If isInCheck is true, iterate the board for all pieces of the same color. For each move for each of these pieces, run the isInCheck function again. If all
        // of them return true, this function returns true
        if (isInCheck(teamColor)){
            for (int i = 1; i <= 8; i++){
                for (int j = 1; j <= 8; j++) {
                    ChessPosition checkedPiecePosition=new ChessPosition((i), (j));
                    if (board.getPiece(checkedPiecePosition) != null ){
                        if (board.getPiece(checkedPiecePosition).getTeamColor() == teamColor){
                            ChessPiece givenPiece = board.getPiece(checkedPiecePosition);
                            Collection<ChessMove> givenPiecePossibleMoves = givenPiece.pieceMoves(board, checkedPiecePosition);
                            for (ChessMove move: givenPiecePossibleMoves){
                                ChessPosition kingPosition = findCurrentKing(board, teamColor);
                                makeMove(move);
                                if (!isInCheck(teamColor)){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void unmakeMove(ChessMove move){
        ChessPiece movingPiece = board.getPiece(move.endPosition);
        board.addPiece(move.endPosition, null);
        board.addPiece(move.startPosition, movingPiece);
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
