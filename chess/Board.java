package chess;

import java.util.ArrayList;
import java.util.List;
import chess.*;

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
                    if (i == 1){
                        ChessBoard[i][j] = new Pawn(Color.WHITE, i, j, true);
                    }else{
                        ChessBoard[i][j] = new Pawn(Color.BLACK, i, j, true);
                    }
                }
            }
        }
    }

    private static void setupBackRow(int row, int col) {
        if (col == 0 || col == 7) {
            if (row == 0) {
                ChessBoard[row][col] = new Rook(Color.WHITE, row, col);
            }else{
                ChessBoard[row][col] = new Rook(Color.BLACK, row, col);
            }    
        } else if (col == 1 || col == 6) {
            if (row == 0) {
                ChessBoard[row][col] = new Knight(Color.WHITE, row, col);
            }else{
                ChessBoard[row][col] = new Knight(Color.BLACK, row, col);
            }   
        } else if (col == 2 || col == 5) {
            if (row == 0) {
                ChessBoard[row][col] = new Bishop(Color.WHITE, row, col);
            }else{
                ChessBoard[row][col] = new Bishop(Color.BLACK, row, col);
            }   
        } else if (col == 3) {
            if (row == 0) {
                ChessBoard[row][col] = new Queen(Color.WHITE, row, col);
            }else{
                ChessBoard[row][col] = new Queen(Color.BLACK, row, col);
            }   
        } else if (col == 4) {
            if (row == 0) {
                ChessBoard[row][col] = new King(Color.WHITE, row, col, true);
            }else{
                ChessBoard[row][col] = new King(Color.BLACK, row, col, true);
            }   
        }
    }

    public static Color getMove() {
        return (isBlackTurn) ? Color.BLACK : Color.WHITE;
    }

    public static Piece GetPiece(String pos) {
        return new Piece(Color.WHITE, 0, 0);
    }

    public static boolean ExistingSpotOnBoard(String pos){
        for(char row = 'a'; row <= 'h'; row++) {
            if (pos.charAt(0) == row)
                for(int num = 1; num <= 8; num++) {
                    if(pos.charAt(1) == num){
                        return true;
                    }
                }
        }
        return false;
    }

    private static boolean isSquareUnderAttack(Color player, ChessCoordinatePair square) {
       
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                Piece boardSquare = ChessBoard[i][j];
                if (boardSquare.isOccupied() && boardSquare.piece.isBlack != (player == Color.BLACK)) {
                   
                    ArrayList<Piece> moves = boardSquare.deepestMovesFrom(new Piece(i, j));
                   
                    if (moves.contains(square)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Piece getKingSquare(Color player) {
       
        for (int i = 0; i < ChessBoard.length; i++) {
            for (int j = 0; j < ChessBoard[i].length; j++) {
                Piece boardSquare = ChessBoard[i][j];
                if (boardSquare.isOccupied() && boardSquare.piece instanceof King && boardSquare.piece.isBlack == (player == Color.BLACK)) {
                    
                    return new Piece(i, j);
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