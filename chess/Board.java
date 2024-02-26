package chess;

import java.util.ArrayList;
import java.util.List;
import chess.Color;
import chess.Piece;
import chess.Rook;

public class Board {

    public static boolean isBlackTurn = true;
    public static int moveCount = 0;
    public static Piece[][] ChessBoard;
    public static int rows = 8;
    public static int cols = 8;

    public static void setupBoard() {
        ChessBoard = new Piece[rows][cols];
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                ChessBoard[i][j] = new BlankSpace(Color.WHITE, i, j);
            }
        }
        setupStandardBoard();
    }

    public static Color GetMove(){
        if (isBlackTurn){
            return Color.BLACK;
        }else{
            return Color.WHITE;
        }
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

    public static Piece GetPiece() {

    }

    private static boolean isSquareUnderAttack(Color player, ChessCoordinatePair square) {
       
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                Piece boardSquare = ChessBoard[i][j];
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
                Piece boardSquare = ChessBoard[i][j];
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
                Piece boardSquare = ChessBoard[i][j];
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