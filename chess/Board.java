package chess;

import java.util.*;

public class Board {

    public static Color playersTurn = Color.WHITE;

    public static ArrayList<Piece> returnPieceArrayList = new ArrayList<>();
    public static Piece[][] chess_set = new Piece[8][8];

    public static HashMap<String, Piece> ChessBoard = new HashMap<String, Piece>(64);

    public static Color getSquareColor(char file, int rank){
        if ((file == 'a' || file == 'c' || file == 'e' || file == 'g') && (rank == 1 || rank == 3 || rank == 5 || rank == 7)){
            return Color.BLACK;
        }
        if ((file == 'b' || file == 'd' || file == 'f' || file == 'h') && (rank == 2 || rank == 4 || rank == 6 || rank == 8)){
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public static void setupBoard() {
        for(char file = 'a'; file <= 'h'; file++) {
            for(int rank = 1; rank <= 8; rank++) {
                String position = Character.toString(file) + Integer.toString(rank);
                if (getSquareColor(file, rank) == Color.WHITE){
                    ChessBoard.put(position, new BlankSpace("  ", Color.WHITE, file, rank));
                }else{
                    ChessBoard.put(position, new BlankSpace("##", Color.BLACK, file, rank));
                }
            }
        }
        setupStandardBoard();
    }

    private static void setupStandardBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 1; rank <= 8; rank++) {
                String position = Character.toString(file) + Integer.toString(rank);

                // White Pawns
                if (rank == 2) {
                    ChessBoard.put(position, new Pawn("wp", Color.WHITE, file, rank, true));
                }
                // Black Pawns
                else if (rank == 7) {
                    ChessBoard.put(position, new Pawn("bp", Color.BLACK, file, rank, true));
                }
                // White Rooks
                else if ((file == 'a' || file == 'h') && rank == 1) {
                    ChessBoard.put(position, new Rook("wR", Color.WHITE, file, rank));
                }
                // Black Rooks
                else if ((file == 'a' || file == 'h') && rank == 8) {
                    ChessBoard.put(position, new Rook("bR", Color.BLACK, file, rank));
                }
                // White Knights
                else if ((file == 'b' || file == 'g') && rank == 1) {
                    ChessBoard.put(position, new Knight("wN", Color.WHITE, file, rank));
                }
                // Black Knights
                else if ((file == 'b' || file == 'g') && rank == 8) {
                    ChessBoard.put(position, new Knight("bN", Color.BLACK, file, rank));
                }
                // White Bishops
                else if ((file == 'c' || file == 'f') && rank == 1) {
                    ChessBoard.put(position, new Bishop("wB", Color.WHITE, file, rank));
                }
                // Black Bishops
                else if ((file == 'c' || file == 'f') && rank == 8) {
                    ChessBoard.put(position, new Bishop("bB", Color.BLACK, file, rank));
                }
                // White Queen
                else if (file == 'd' && rank == 1) {
                    ChessBoard.put(position, new Queen("wQ", Color.WHITE, file, rank));
                }
                // Black Queen
                else if (file == 'd' && rank == 8) {
                    ChessBoard.put(position, new Queen("bQ", Color.BLACK, file, rank));
                }
                // White King
                else if (file == 'e' && rank == 1) {
                    ChessBoard.put(position, new King("wK", Color.WHITE, file, rank, true));
                }
                // Black King
                else if (file == 'e' && rank == 8) {
                    ChessBoard.put(position, new King("bK", Color.BLACK, file, rank, true));
                }
            }
        }
    }

    public static Color getMove(){
        if (playersTurn == Color.BLACK){
            return Color.BLACK;
        }else{
            return Color.WHITE;
        }
    }

    public static void switchTurn(){
        playersTurn = (playersTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public static Piece getPiece(String pos) {
        return ChessBoard.get(pos);
    }

    public static boolean existingSpotOnBoard(String pos){
        return ChessBoard.containsKey(pos);
    }

    public static boolean isCheckmate(Color player) {
        String kingPos = getKingPosition(player);
        Piece kingPiece = ChessBoard.get(kingPos);
        return kingPiece.isInCheckmate(kingPos);
    }

    public static String getKingPosition(Color player){
        for(String pos: ChessBoard.keySet()){
            Piece tempPiece = ChessBoard.get(pos);
            if ((tempPiece instanceof King) && (tempPiece.getColor() == player)){
                return pos;
            }
        }
        return null; // No kings on board. Game should be restarted.
    }

    public static boolean isCheck(Color player) {
        String kingPos = getKingPosition(player);
        if (kingPos == null) // No king found
            return false;
        
        for (String pos: ChessBoard.keySet()){
            Piece tempPiece = ChessBoard.get(pos);
            if ((tempPiece.getColor() != player) && (tempPiece.ableToMove(pos, kingPos))){
                return true;
            }
        }
        return false;
    }

    public static boolean isBlankSpace(String pos){
        return (ChessBoard.get(pos) instanceof BlankSpace);
    }
}
