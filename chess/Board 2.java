package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
    enum Color {WHITE, BLACK;
        enum Player {WHITE, BLACK};

        public Color getOpponentColor() {
            return this == WHITE ? BLACK : WHITE;
        }
    }

    static class ChessCoordinatePair {
        int rank;
        int file;

        public ChessCoordinatePair(int rank, int file) {
            this.rank = rank;
            this.file = file;
        }
    }

    static abstract class Piece {
        boolean isBlack;

        public abstract ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress);
    }

    static class Pawn extends Piece {
        public Pawn(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class Rook extends Piece {
        public Rook(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class Knight extends Piece {
        public Knight(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class Bishop extends Piece {
        public Bishop(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class Queen extends Piece {
        public Queen(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class King extends Piece {
        public King(boolean isBlack) {
            this.isBlack = isBlack;
        }

        @Override
        public ArrayList<ChessCoordinatePair> deepestMovesFrom(ChessCoordinatePair startAddress) {
            return new ArrayList<>();
        }
    }

    static class ChessBoardSquare {
        Piece piece;

        public ChessBoardSquare(ChessCoordinatePair coordinatePair) {
            this.piece = null;
        }

        public boolean isOccupied() {
            return piece != null;
        }

        @Override
        public String toString() {
            return (piece != null) ? piece.getClass().getSimpleName() : "-";
        }
    }

    static class ChessNamingConstants {
        public static final String ROOK = "ROOK";
        public static final String KNIGHT = "KNIGHT";
        public static final String BISHOP = "BISHOP";
    }

    public static int rows = 8;
    public static int cols = 8;
    public static boolean isBlackTurn = true;
    public static int moveCount = 0;
    public static ChessBoardSquare[][] ChessBoard;

    public static void setupBoard() {
        ChessBoard = new ChessBoardSquare[rows][cols];
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoard[i][j] = new ChessBoardSquare(new ChessCoordinatePair(i, j));
            }
        }
        setupStandardBoard();
    }

    private static void setupStandardBoard() {
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                if (i == 0 || i == 7) {
                    setupBackRow(i, j);
                } else if (i == 1 || i == 6) {
                    ChessBoard[i][j].piece = new Pawn(i == 1);
                }
            }
        }
    }

    private static void setupBackRow(int row, int col) {
        if (col == 0 || col == 7) {
            ChessBoard[row][col].piece = new Rook(row == 0);
        } else if (col == 1 || col == 6) {
            ChessBoard[row][col].piece = new Knight(row == 0);
        } else if (col == 2 || col == 5) {
            ChessBoard[row][col].piece = new Bishop(row == 0);
        } else if (col == 3) {
            ChessBoard[row][col].piece = new Queen(row == 0);
        } else if (col == 4) {
            ChessBoard[row][col].piece = new King(row == 0);
        }
    }

    public static Color getMove() {
        return (isBlackTurn) ? Color.BLACK : Color.WHITE;
    }

    private static boolean isSquareUnderAttack(Color player, ChessCoordinatePair square) {
       
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoardSquare boardSquare = ChessBoard[i][j];
                if (boardSquare.isOccupied() && boardSquare.piece.isBlack != (player == Color.BLACK)) {
                   
                    ArrayList<ChessCoordinatePair> moves = boardSquare.piece.deepestMovesFrom(new ChessCoordinatePair(i, j));
                   
                    if (moves.contains(square)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static ChessCoordinatePair getKingSquare(Color player) {
       
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoardSquare boardSquare = ChessBoard[i][j];
                if (boardSquare.isOccupied() && boardSquare.piece instanceof King && boardSquare.piece.isBlack == (player == Color.BLACK)) {
                    
                    return new ChessCoordinatePair(i, j);
                }
            }
        }
        return null; 
    }

    private static List<Move> getLegalMoves(Color player) {
        List<Move> legalMoves = new ArrayList<>();
        
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoardSquare boardSquare = ChessBoard[i][j];
                if (boardSquare.isOccupied() && boardSquare.piece.isBlack == (player == Color.BLACK)) {
                    
                    ArrayList<ChessCoordinatePair> moves = boardSquare.piece.deepestMovesFrom(new ChessCoordinatePair(i, j));
                   
                    for (ChessCoordinatePair move : moves) {
                        legalMoves.add(new Move(boardSquare.piece, new ChessCoordinatePair(i, j), move));
                    }
                }
            }
        }
        return legalMoves;
    }

    public static boolean isCheckmate(Color player) {
        if (isCheck(player) && !hasLegalMoves(player)) {
            return true;
        }
        return false;
    }

    private static boolean isCheck(Color player) {
        ChessCoordinatePair kingSquare = getKingSquare(player);
        return isSquareUnderAttack(player, kingSquare);
    }

    private static boolean hasLegalMoves(Color player) {
        List<Move> legalMoves = getLegalMoves(player);
        return !legalMoves.isEmpty();
    }

    private static boolean isKingUnderAttack(Color player) {
        ChessCoordinatePair kingSquare = getKingSquare(player);
        return isSquareUnderAttack(player.getOpponentColor(), kingSquare);
    }

    static class Move {
        Piece piece;
        ChessCoordinatePair start;
        ChessCoordinatePair end;

        public Move(Piece piece, ChessCoordinatePair start, ChessCoordinatePair end) {
            this.piece = piece;
            this.start = start;
            this.end = end;
        }
    }
}
