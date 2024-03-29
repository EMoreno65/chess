package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private PieceType type;

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that=(ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

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
    public Collection<ChessMove> diagonalMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();

        // Iterate over each direction
        addMoves(board, myPosition, 1, 1, possibleMoves);
        addMoves(board, myPosition, 1, -1, possibleMoves);
        addMoves(board, myPosition, -1, 1, possibleMoves);
        addMoves(board, myPosition, -1, -1, possibleMoves);

        return possibleMoves;
    }
    public Collection<ChessMove> straightMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();

        // Iterate over each direction
        addMoves(board, myPosition, 1, 0, possibleMoves); // Forward
        addMoves(board, myPosition, -1, 0, possibleMoves); // Backward
        addMoves(board, myPosition, 0, 1, possibleMoves); // Right
        addMoves(board, myPosition, 0, -1, possibleMoves); // Left

        return possibleMoves;
    }

    private void addMoves(ChessBoard board, ChessPosition myPosition, int rowDirection, int colDirection, HashSet<ChessMove> possibleMoves) {
        int i = myPosition.getRow();
        int j = myPosition.getColumn();

        while (i >= 1 && i <= 8 && j >= 1 && j <= 8) {
            if (i != myPosition.getRow() || j != myPosition.getColumn()) {
                ChessPosition currentPosition = new ChessPosition(i, j);
                ChessPiece pieceAtPosition = board.getPiece(currentPosition);
                if (pieceAtPosition != null) {
                    if (pieceAtPosition.pieceColor == this.pieceColor) {
                        break;
                    } else {
                        ChessMove addedMove = new ChessMove(myPosition, currentPosition, null);
                        possibleMoves.add(addedMove);
                        break;
                    }
                }
                ChessMove addedMove = new ChessMove(myPosition, currentPosition, null);
                possibleMoves.add(addedMove);
            }
            i += rowDirection;
            j += colDirection;
        }
    }

    public Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        addMovesforKnightorKing(board, myPosition, possibleMoves, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}});

        return possibleMoves;
    }

    public Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();

        // Knight moves offsets
        int[][] offsets = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        addMovesforKnightorKing(board, myPosition, possibleMoves, offsets);

        return possibleMoves;
    }

    private void addMovesforKnightorKing(ChessBoard board, ChessPosition myPosition, HashSet<ChessMove> possibleMoves, int[][] offsets) {
        for (int[] offset : offsets) {
            int i = myPosition.getRow() + offset[0];
            int j = myPosition.getColumn() + offset[1];
            if (isValidPosition(i, j)) {
                ChessPosition currentPosition = new ChessPosition(i, j);
                ChessPiece pieceAtPosition = board.getPiece(currentPosition);
                if (pieceAtPosition == null || pieceAtPosition.pieceColor != this.pieceColor) {
                    ChessMove addedMove = new ChessMove(myPosition, currentPosition, null);
                    possibleMoves.add(addedMove);
                }
            }
        }
    }

    private boolean isValidPosition(int i, int j) {
        return i >= 1 && i <= 8 && j >= 1 && j <= 8;
    }

    public Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> possibleMoves=new HashSet<>();
        if (this.pieceColor == ChessGame.TeamColor.WHITE && myPosition.getRow() < 8) {
            if (myPosition.getRow() == 2) {
                ChessPosition newPositionTwoSpace=new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                ChessPosition newPositionOneSpace=new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
                ChessPiece pieceAtPositionOneSpaceAhead=board.getPiece(newPositionOneSpace);
                ChessPiece pieceAtPositionTwoSpaces=board.getPiece(newPositionTwoSpace);
                if (pieceAtPositionOneSpaceAhead == null && pieceAtPositionTwoSpaces == null) {
                    ChessMove addedMove=new ChessMove(myPosition, newPositionTwoSpace, null);
                    possibleMoves.add(addedMove);
                }
            }
            ChessPosition newPosition=new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            ChessPiece pieceAtPosition2=board.getPiece(newPosition);
            if (pieceAtPosition2 == null) {
                if (newPosition.getRow() == 8) {
                    ChessMove addedMove=new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                    possibleMoves.add(addedMove);
                    ChessMove addedMove2=new ChessMove(myPosition, newPosition, PieceType.ROOK);
                    possibleMoves.add(addedMove2);
                    ChessMove addedMove3=new ChessMove(myPosition, newPosition, PieceType.QUEEN);
                    possibleMoves.add(addedMove3);
                    ChessMove addedMove4=new ChessMove(myPosition, newPosition, PieceType.KNIGHT);
                    possibleMoves.add(addedMove4);
                }
            }
            if (myPosition.getColumn() != 1) {
                ChessPosition leftDiagonalPosition=new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                ChessPiece pieceAtLeftPosition=board.getPiece(leftDiagonalPosition);
                if (pieceAtLeftPosition != null) {
                    if (pieceAtLeftPosition.pieceColor != this.pieceColor) {
                        ChessMove addedMove=new ChessMove(myPosition, leftDiagonalPosition, null);
                        if (leftDiagonalPosition.getRow() != 8) {
                            possibleMoves.add(addedMove);
                        } else if (leftDiagonalPosition.getRow() == 8) {
                            ChessMove leftAddedMove1=new ChessMove(myPosition, leftDiagonalPosition, PieceType.BISHOP);
                            possibleMoves.add(leftAddedMove1);
                            ChessMove leftAddedMove2=new ChessMove(myPosition, leftDiagonalPosition, PieceType.ROOK);
                            possibleMoves.add(leftAddedMove2);
                            ChessMove leftAddedMove3=new ChessMove(myPosition, leftDiagonalPosition, PieceType.QUEEN);
                            possibleMoves.add(leftAddedMove3);
                            ChessMove leftAddedMove4=new ChessMove(myPosition, leftDiagonalPosition, PieceType.KNIGHT);
                            possibleMoves.add(leftAddedMove4);
                        }
                    }
                }
            }
            if (myPosition.getColumn() != 8) {
                ChessPosition rightDiagonalPosition=new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
                ChessPiece pieceAtRightPosition=board.getPiece(rightDiagonalPosition);
                if (pieceAtRightPosition != null) {
                    if (pieceAtRightPosition.pieceColor != this.pieceColor) {
                        ChessMove addedMove=new ChessMove(myPosition, rightDiagonalPosition, null);
                        if (rightDiagonalPosition.getRow() == 8) {
                            ChessMove rightAddedMove1=new ChessMove(myPosition, rightDiagonalPosition, PieceType.BISHOP);
                            possibleMoves.add(rightAddedMove1);
                            ChessMove rightAddedMove2=new ChessMove(myPosition, rightDiagonalPosition, PieceType.ROOK);
                            possibleMoves.add(rightAddedMove2);
                            ChessMove rightAddedMove3=new ChessMove(myPosition, rightDiagonalPosition, PieceType.QUEEN);
                            possibleMoves.add(rightAddedMove3);
                            ChessMove rightAddedMove4=new ChessMove(myPosition, rightDiagonalPosition, PieceType.KNIGHT);
                            possibleMoves.add(rightAddedMove4);
                        } else {
                            possibleMoves.add(addedMove);
                        }
                    }
                }
            }
            if (newPosition.getRow() != 8 && pieceAtPosition2 == null) {
                ChessMove addedMove=new ChessMove(myPosition, newPosition, null);
                possibleMoves.add(addedMove);
            }
        }
        if (this.pieceColor == ChessGame.TeamColor.BLACK && myPosition.getRow() > 1) {
            if (myPosition.getRow() == 7) {
                ChessPosition newPositionTwoSpace=new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                ChessPosition newPositionOneSpace=new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
                ChessPiece pieceAtPositionOneSpaceAhead=board.getPiece(newPositionOneSpace);
                ChessPiece pieceAtPositionTwoSpaces=board.getPiece(newPositionTwoSpace);
                if (pieceAtPositionOneSpaceAhead == null && pieceAtPositionTwoSpaces == null) {
                    ChessMove addedMove=new ChessMove(myPosition, newPositionTwoSpace, null);
                    possibleMoves.add(addedMove);
                }
            }
            ChessPosition newPosition=new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            ChessPiece pieceAtPosition2=board.getPiece(newPosition);
            if (pieceAtPosition2 == null) {
                if (newPosition.getRow() == 1) {
                    ChessMove addedMove=new ChessMove(myPosition, newPosition, PieceType.BISHOP);
                    possibleMoves.add(addedMove);
                    ChessMove addedMove2=new ChessMove(myPosition, newPosition, PieceType.ROOK);
                    possibleMoves.add(addedMove2);
                    ChessMove addedMove3=new ChessMove(myPosition, newPosition, PieceType.QUEEN);
                    possibleMoves.add(addedMove3);
                    ChessMove addedMove4=new ChessMove(myPosition, newPosition, PieceType.KNIGHT);
                    possibleMoves.add(addedMove4);
                }
            }
            if (myPosition.getColumn() != 8) {
                ChessPosition leftDiagonalPosition=new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                ChessPiece pieceAtLeftPosition=board.getPiece(leftDiagonalPosition);
                if (pieceAtLeftPosition != null) {
                    if (pieceAtLeftPosition.pieceColor != this.pieceColor) {
                        ChessMove addedMove=new ChessMove(myPosition, leftDiagonalPosition, null);
                        if (leftDiagonalPosition.getRow() != 1) {
                            possibleMoves.add(addedMove);
                        } else if (leftDiagonalPosition.getRow() == 1) {
                            ChessMove leftAddedMove1=new ChessMove(myPosition, leftDiagonalPosition, PieceType.BISHOP);
                            possibleMoves.add(leftAddedMove1);
                            ChessMove leftAddedMove2=new ChessMove(myPosition, leftDiagonalPosition, PieceType.ROOK);
                            possibleMoves.add(leftAddedMove2);
                            ChessMove leftAddedMove3=new ChessMove(myPosition, leftDiagonalPosition, PieceType.QUEEN);
                            possibleMoves.add(leftAddedMove3);
                            ChessMove leftAddedMove4=new ChessMove(myPosition, leftDiagonalPosition, PieceType.KNIGHT);
                            possibleMoves.add(leftAddedMove4);
                        }
                    }
                }
            }
            if (myPosition.getColumn() != 1) {
                ChessPosition rightDiagonalPosition=new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                ChessPiece pieceAtRightPosition=board.getPiece(rightDiagonalPosition);
                if (pieceAtRightPosition != null) {
                    if (pieceAtRightPosition.pieceColor != this.pieceColor) {
                        ChessMove addedMove=new ChessMove(myPosition, rightDiagonalPosition, null);
                        if (rightDiagonalPosition.getRow() == 1) {
                            ChessMove rightAddedMove1=new ChessMove(myPosition, rightDiagonalPosition, PieceType.BISHOP);
                            possibleMoves.add(rightAddedMove1);
                            ChessMove rightAddedMove2=new ChessMove(myPosition, rightDiagonalPosition, PieceType.ROOK);
                            possibleMoves.add(rightAddedMove2);
                            ChessMove rightAddedMove3=new ChessMove(myPosition, rightDiagonalPosition, PieceType.QUEEN);
                            possibleMoves.add(rightAddedMove3);
                            ChessMove rightAddedMove4=new ChessMove(myPosition, rightDiagonalPosition, PieceType.KNIGHT);
                            possibleMoves.add(rightAddedMove4);
                        } else {
                            possibleMoves.add(addedMove);
                        }
                    }
                }
            }
            if (newPosition.getRow() != 1 && pieceAtPosition2 == null) {
                ChessMove addedMove=new ChessMove(myPosition, newPosition, null);
                possibleMoves.add(addedMove);
            }
        }
        return possibleMoves;
    }}
