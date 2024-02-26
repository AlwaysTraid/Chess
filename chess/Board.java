import java.util.ArrayList;

public class Board {

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
    public boolean isBlackTurn = true;
    public int moveCount = 0;
    public ChessBoardSquare[][] ChessBoard;

    public Board() {
        ChessBoard = new ChessBoardSquare[rows][cols];
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoard[i][j] = new ChessBoardSquare(new ChessCoordinatePair(i, j));
            }
        }
        setupStandardBoard();
    }

    private void setupStandardBoard() {
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

    private void setupBackRow(int row, int col) {
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

    public boolean getIsBlackTurn() {
        return !isBlackTurn;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                str.append(ChessBoard[i][j].toString()).append(" ");
            }
            str.append(i + 1).append("\n");
        }
        str.append(" a  b  c  d  e  f  g  h\n");
        return str.toString();
    }

    private void move(ChessCoordinatePair startAddress, ChessCoordinatePair endAddress) {
        ChessBoard[endAddress.rank][endAddress.file].piece = ChessBoard[startAddress.rank][startAddress.file].piece;
        ChessBoard[startAddress.rank][startAddress.file].piece = null;
        moveCount++;
        isBlackTurn = (moveCount % 2 == 0);
    }

    private Piece getChessPieceFrom(String CHESS_PIECE_CONSTANT) {
        if (CHESS_PIECE_CONSTANT.equals(ChessNamingConstants.ROOK)) {
            return new Rook(!isBlackTurn);
        } else if (CHESS_PIECE_CONSTANT.equals(ChessNamingConstants.KNIGHT)) {
            return new Knight(!isBlackTurn);
        } else if (CHESS_PIECE_CONSTANT.equals(ChessNamingConstants.BISHOP)) {
            return new Bishop(!isBlackTurn);
        } else {
            return new Queen(!isBlackTurn);
        }
    }

    private boolean kingIsInCheck() {
        for (int currentRank = 0; currentRank < ChessBoard.length; currentRank++) {
            for (int currentFile = 0; currentFile < ChessBoard[currentRank].length; currentFile++) {
                if (ChessBoard[currentRank][currentFile].isOccupied() && ChessBoard[currentRank][currentFile].piece.isBlack == isBlackTurn) {
                    return isPieceCollidingWithKingAt(currentRank, currentFile);
                }
            }
        }
        return false;
    }

    private boolean isPieceCollidingWithKingAt(int rank, int file) {
        Piece cp = ChessBoard[rank][file].piece;
        ChessCoordinatePair startAddress = new ChessCoordinatePair(rank, file);
        ArrayList<ChessCoordinatePair> deepestMoves = cp.deepestMovesFrom(startAddress);
        for (ChessCoordinatePair deepestMove : deepestMoves) {
            if (kingIsFirstCollisionInPathBetween(startAddress, deepestMove)) {
                return true;
            }
        }
        return false;
    }

    private boolean kingIsFirstCollisionInPathBetween(ChessCoordinatePair start, ChessCoordinatePair end) {
        int startRank = start.rank;
        int startFile = start.file;
        int endRank = end.rank;
        int endFile = end.file;

        // Horizontal
        if (startRank == endRank) {
            int startFileStep = (startFile < endFile) ? 1 : -1;
            for (int file = startFile + startFileStep; file != endFile; file += startFileStep) {
                if (ChessBoard[startRank][file].isOccupied()) {
                    return true;
                }
            }
        }

        // Vertical
        else if (startFile == endFile) {
            int startRankStep = (startRank < endRank) ? 1 : -1;
            for (int rank = startRank + startRankStep; rank != endRank; rank += startRankStep) {
                if (ChessBoard[rank][startFile].isOccupied()) {
                    return true;
                }
            }
        }

        // Diagonal
        else if (Math.abs(startRank - endRank) == Math.abs(startFile - endFile)) {
            int startRankStep = (startRank < endRank) ? 1 : -1;
            int startFileStep = (startFile < endFile) ? 1 : -1;
            for (int rank = startRank + startRankStep, file = startFile + startFileStep; rank != endRank && file != endFile; rank += startRankStep, file += startFileStep) {
                if (ChessBoard[rank][file].isOccupied()) {
                    return true;
                }
            }
        }
        return false;
    }
}
