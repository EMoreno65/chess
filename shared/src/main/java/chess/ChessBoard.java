package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    @Override
    public String toString() {
        return "ChessBoard{" +
                "squares=" + Arrays.toString(squares) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that=(ChessBoard) o;
        return Arrays.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(squares);
    }
    private ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {
    }
    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow() -1][position.getColumn() - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece whitePawn1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn3 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn4 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn5 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn6 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn7 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece whitePawn8 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn3 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn4 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn5 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn6 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn7 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece blackPawn8 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        ChessPiece whiteRook1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPiece whiteRook2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPiece whiteKnight1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPiece whiteKnight2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPiece whiteBishop1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPiece whiteBishop2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPiece whiteQueen = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPiece whiteKing = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPiece blackRook1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPiece blackRook2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPiece blackKnight1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPiece blackKnight2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPiece blackBishop1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPiece blackBishop2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPiece blackQueen = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        ChessPiece blackKing = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = null;
            }
        }
        ChessPosition initialPosition1=new ChessPosition(1, 1);
        addPiece(initialPosition1, whiteRook1);
        ChessPosition initialPosition2=new ChessPosition(1, 2);
        addPiece(initialPosition2, whiteKnight1);
        ChessPosition initialPosition3=new ChessPosition(1, 3);
        addPiece(initialPosition3, whiteBishop1);
        ChessPosition initialPosition4=new ChessPosition(1, 4);
        addPiece(initialPosition4, whiteQueen);
        ChessPosition initialPosition5=new ChessPosition(1, 5);
        addPiece(initialPosition5, whiteKing);
        ChessPosition initialPosition6=new ChessPosition(1, 6);
        addPiece(initialPosition6, whiteBishop2);
        ChessPosition initialPosition7=new ChessPosition(1, 7);
        addPiece(initialPosition7, whiteKnight2);
        ChessPosition initialPosition8=new ChessPosition(1, 8);
        addPiece(initialPosition8, whiteRook2);

        ChessPosition initialPosition9=new ChessPosition(2, 1);
        addPiece(initialPosition9, whitePawn1);
        ChessPosition initialPosition10=new ChessPosition(2, 2);
        addPiece(initialPosition10, whitePawn2);
        ChessPosition initialPosition11=new ChessPosition(2, 3);
        addPiece(initialPosition11, whitePawn3);
        ChessPosition initialPosition12=new ChessPosition(2, 4);
        addPiece(initialPosition12, whitePawn4);
        ChessPosition initialPosition13=new ChessPosition(2, 5);
        addPiece(initialPosition13, whitePawn5);
        ChessPosition initialPosition14=new ChessPosition(2, 6);
        addPiece(initialPosition14, whitePawn6);
        ChessPosition initialPosition15=new ChessPosition(2, 7);
        addPiece(initialPosition15, whitePawn7);
        ChessPosition initialPosition16=new ChessPosition(2, 8);
        addPiece(initialPosition16, whitePawn8);

        ChessPosition initialPosition17=new ChessPosition(8, 1);
        addPiece(initialPosition17, blackRook1);
        ChessPosition initialPosition18=new ChessPosition(8, 2);
        addPiece(initialPosition18, blackKnight1);
        ChessPosition initialPosition19=new ChessPosition(8, 3);
        addPiece(initialPosition19, blackBishop1);
        ChessPosition initialPosition20=new ChessPosition(8, 4);
        addPiece(initialPosition20, blackQueen);
        ChessPosition initialPosition21=new ChessPosition(8, 5);
        addPiece(initialPosition21, blackKing);
        ChessPosition initialPosition22=new ChessPosition(8, 6);
        addPiece(initialPosition22, blackBishop2);
        ChessPosition initialPosition23=new ChessPosition(8, 7);
        addPiece(initialPosition23, blackKnight2);
        ChessPosition initialPosition24=new ChessPosition(8, 8);
        addPiece(initialPosition24, blackRook2);

        ChessPosition initialPosition25=new ChessPosition(7, 1);
        addPiece(initialPosition25, blackPawn1);
        ChessPosition initialPosition26=new ChessPosition(7, 2);
        addPiece(initialPosition26, blackPawn2);
        ChessPosition initialPosition27=new ChessPosition(7, 3);
        addPiece(initialPosition27, blackPawn3);
        ChessPosition initialPosition28=new ChessPosition(7, 4);
        addPiece(initialPosition28, blackPawn4);
        ChessPosition initialPosition29=new ChessPosition(7, 5);
        addPiece(initialPosition29, blackPawn5);
        ChessPosition initialPosition30=new ChessPosition(7, 6);
        addPiece(initialPosition30, blackPawn6);
        ChessPosition initialPosition31=new ChessPosition(7, 7);
        addPiece(initialPosition31, blackPawn7);
        ChessPosition initialPosition32=new ChessPosition(7, 8);
        addPiece(initialPosition32, blackPawn8);

        System.out.println(Arrays.deepToString(squares));
    }
}
