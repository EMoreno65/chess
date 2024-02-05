package chess;

import java.util.ArrayList;
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
            Collection<ChessMove> kingValidDeletedMoves = new ArrayList<>();
            kingValidMoves = board.getPiece(startPosition).kingMoves(board, startPosition);
            for (ChessMove move: kingValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid moves
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from kingValidMoves, how?
                    kingValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
            }
            kingValidMoves.removeAll(kingValidDeletedMoves);
            return kingValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.QUEEN){
            Collection<ChessMove> queenValidMoves = null;
            Collection<ChessMove> queenValidDeletedMoves = new ArrayList<>();
            queenValidMoves = (board.getPiece(startPosition).diagonalMoves(board, startPosition));
            queenValidMoves.addAll(board.getPiece(startPosition).straightMoves(board, startPosition));
            for (ChessMove move: queenValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid moves
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from kingValidMoves, how?
                    queenValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
            }
            queenValidMoves.removeAll(queenValidDeletedMoves);
            return queenValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.BISHOP){
            Collection<ChessMove> bishopValidMoves = null;
            Collection<ChessMove> bishopValidDeletedMoves = new ArrayList<>();
            bishopValidMoves = board.getPiece(startPosition).diagonalMoves(board, startPosition);
            for (ChessMove move: bishopValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid move
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from kingValidMoves, how?
                    bishopValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
            }
            bishopValidMoves.removeAll(bishopValidDeletedMoves);
            return bishopValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.KNIGHT){
            Collection<ChessMove> knightValidMoves = null;
            Collection<ChessMove> knightValidDeletedMoves = new ArrayList<>();
            knightValidMoves = board.getPiece(startPosition).knightMoves(board, startPosition);
            for (ChessMove move: knightValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid move
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from knightValidMoves, how?
                    knightValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
            }
            knightValidMoves.removeAll(knightValidDeletedMoves);
            return knightValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.ROOK){
            Collection<ChessMove> rookValidMoves = null;
            Collection<ChessMove> rookValidDeletedMoves = new ArrayList<>();
            rookValidMoves = board.getPiece(startPosition).straightMoves(board, startPosition);
            for (ChessMove move: rookValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid moves
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from kingValidMoves, how?
                    rookValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
                }
            rookValidMoves.removeAll(rookValidDeletedMoves);
            return rookValidMoves;
        }
        if (board.getPiece(startPosition).getPieceType() == ChessPiece.PieceType.PAWN){
            Collection<ChessMove> pawnValidMoves = null;
            Collection<ChessMove> pawnValidDeletedMoves = new ArrayList<>();
            pawnValidMoves = board.getPiece(startPosition).pawnMoves(board, startPosition);
            for (ChessMove move: pawnValidMoves){
                // Iterate through the moves of the opposing team pieces, if any of them match
                // the king's position, return true for isInCheck and don't add the move to valid moves
                ChessPiece restoredPiece = null;
                if (board.getPiece(move.endPosition) != null){
                    restoredPiece = board.getPiece(move.endPosition);
                }
                ChessPiece movingPiece = board.getPiece(move.startPosition);
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
                if (isInCheck(board.getPiece(move.endPosition).getTeamColor())){
                    // delete the move from kingValidMoves, how?
                    pawnValidDeletedMoves.add(move);
                }
                ChessPiece unmovingPiece = board.getPiece(move.endPosition);
                board.addPiece(move.endPosition, restoredPiece);
                board.addPiece(move.startPosition, unmovingPiece);
            }
            pawnValidMoves.removeAll(pawnValidDeletedMoves);
            return pawnValidMoves;
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
        if (board.getPiece(move.startPosition).getTeamColor() != currentTurn){
            throw new InvalidMoveException("Invalid Move: Out of Turn");
        }
        if (validMoves(move.startPosition).contains(move)){
            ChessPiece movingPiece = board.getPiece(move.startPosition);
            if (move.endPosition.getRow() == 8 && board.getPiece(move.startPosition).getTeamColor() == TeamColor.WHITE && board.getPiece(move.startPosition).getPieceType() == ChessPiece.PieceType.PAWN) {
                board.addPiece(move.startPosition, null);
                ChessPiece promotedPiece = new ChessPiece(TeamColor.WHITE, move.promotionPiece);
                board.addPiece(move.endPosition, promotedPiece);
            }
            else if (move.endPosition.getRow() == 1 && board.getPiece(move.startPosition).getTeamColor() == TeamColor.BLACK && board.getPiece(move.startPosition).getPieceType() == ChessPiece.PieceType.PAWN) {
                board.addPiece(move.startPosition, null);
                ChessPiece promotedPiece = new ChessPiece(TeamColor.BLACK, move.promotionPiece);
                board.addPiece(move.endPosition, promotedPiece);
            }
            else{
                board.addPiece(move.startPosition, null);
                board.addPiece(move.endPosition, movingPiece);
            }
            if (currentTurn == TeamColor.WHITE){
                currentTurn = TeamColor.BLACK;
            }
            else if (currentTurn == TeamColor.BLACK){
                currentTurn = TeamColor.WHITE;
            }
        }
        else{
            throw new InvalidMoveException("Invalid Move");
        }
    }
    public void unmakeMove(ChessMove move, ChessPiece piece) throws InvalidMoveException{
        ChessPiece movingPiece = board.getPiece(move.endPosition);
        board.addPiece(move.endPosition, piece);
        board.addPiece(move.startPosition, movingPiece);
        if (currentTurn == TeamColor.WHITE){
            currentTurn = TeamColor.BLACK;
        }
        else if (currentTurn == TeamColor.BLACK){
            currentTurn = TeamColor.WHITE;
        }

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
    public boolean isInCheckmate(TeamColor teamColor) {
        // If isInCheck is true, iterate the board for all pieces of the same color.
        // For each move for each of these pieces, run the isInCheck function again.
        // If all of them return true, this function returns true
        if (isInCheck(teamColor)) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    ChessPosition checkedPiecePosition = new ChessPosition(i, j);
                    if (board.getPiece(checkedPiecePosition) != null) {
                        if (board.getPiece(checkedPiecePosition).getTeamColor() == teamColor) {
                            ChessPiece givenPiece = board.getPiece(checkedPiecePosition);
                            Collection<ChessMove> givenPiecePossibleMoves = givenPiece.pieceMoves(board, checkedPiecePosition);

                            boolean allMovesInvalid = true; // Assume all moves are invalid initially

                            for (ChessMove move : givenPiecePossibleMoves) {
                                ChessPosition kingPosition = findCurrentKing(board, teamColor);
                                ChessPiece restoredPiece = null;
                                if (board.getPiece(move.endPosition) != null) {
                                    restoredPiece = board.getPiece(move.endPosition);
                                }

                                try {
                                    makeMove(move);
                                } catch (InvalidMoveException e) {
                                    // If any move is invalid, catch the exception
                                    // and continue checking the next move
                                    continue;
                                }

                                // If we reach here, the move is valid, so set allMovesInvalid to false
                                allMovesInvalid = false;

                                try {
                                    unmakeMove(move, restoredPiece);
                                } catch (InvalidMoveException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            // If all moves for the piece are invalid, return true for isCheckmate
                            if (allMovesInvalid) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }


    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
//        run through the pieceMoves of the teamColor king, return valid moves
            ChessPosition testingPosition=findCurrentKing(board, teamColor);
            Collection<ChessMove> anyValidMoves=validMoves(testingPosition);
            if (anyValidMoves.size() == 0) {
                return true;
            }
            return false;
        }
        return false;
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
