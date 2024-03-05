package chess;

import java.util.*;
import chess.*;

public class Board {

    public static Color playersTurn = Color.WHITE;

    public static ArrayList<ReturnPiece> returnPieceArrayList = new ArrayList<>();
    public static ReturnPiece[][] chess_set = new ReturnPiece[8][8];

    public static HashMap<String, Piece> ChessBoard = new HashMap<String, Piece>(64);
    //public static Piece[][] ChessBoard;

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
        // ChessBoard = new Piece[files][ranks];
        // for (int i = 0; i < ChessBoard.length; i++) {
        //     for (int j = 0; j < ChessBoard[i].length; j++) {
        //         ChessBoard[i][j] = new BlankSpace(Color.WHITE, i, j);
        //     }
        // }
        setupStandardBoard();
    }

    private static void setupStandardBoard() {
        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 1; rank <= 8; rank++) {
                String position = Character.toString(file) + Integer.toString(rank);

                //White Pawns
                if (rank == 2) {
                    ChessBoard.put(position, new Pawn("wp", Color.WHITE, file, rank, true));
                }
                //Black Pawns
                else if (rank == 7) {
                    ChessBoard.put(position, new Pawn("bp", Color.BLACK, file, rank, true));
                }
                //White Rooks
                else if ((file == 'a' || file == 'h') && rank == 1) {
                    ChessBoard.put(position, new Rook("wR", Color.WHITE, file, rank));
                }
                //Black Rooks
                else if ((file == 'a' || file == 'h') && rank == 8) {
                    ChessBoard.put(position, new Rook("bR", Color.BLACK, file, rank));
                }
                //White Knights
                else if ((file == 'b' || file == 'g') && rank == 1) {
                    ChessBoard.put(position, new Knight("wN", Color.WHITE, file, rank));
                }
                //Black Knights
                else if ((file == 'b' || file == 'g') && rank == 8) {
                    ChessBoard.put(position, new Knight("bN", Color.BLACK, file, rank));
                }
                //White Bishops
                else if ((file == 'c' || file == 'f') && rank == 1) {
                    ChessBoard.put(position, new Bishop("wB", Color.WHITE, file, rank));
                }
                //Black Bishops
                else if ((file == 'b' || file == 'g') && rank == 8) {
                    ChessBoard.put(position, new Bishop("bB", Color.BLACK, file, rank));
                }
                //White Queen
                else if (file == 'd' && rank == 1) {
                    ChessBoard.put(position, new Queen("wQ", Color.WHITE, file, rank));
                }
                //Black Queen
                else if (file == 'd' && rank == 8) {
                    ChessBoard.put(position, new Queen("bQ", Color.BLACK, file, rank));
                }
                //White King
                else if (file == 'd' && rank == 1) {
                    ChessBoard.put(position, new King("wK", Color.WHITE, file, rank, true));
                }
                //Black King
                else if (file == 'd' && rank == 8) {
                    ChessBoard.put(position, new King("bK", Color.BLACK, file, rank, true));
                }
            }
        }
    }

    public static Color GetMove(){
        if (playersTurn == Color.BLACK){
            return Color.BLACK;
        }else{
            return Color.WHITE;
        }
    }

    public static void switchTurn(){
        if(playersTurn == Color.WHITE){
            playersTurn = Color.BLACK;
        }else{
            playersTurn = Color.WHITE;
        }
    }

    public static Piece GetPiece(String pos) {
        Piece getterPiece = ChessBoard.get(pos);
        return getterPiece;
    }

    public static boolean ExistingSpotOnBoard(String pos){
        return ChessBoard.containsKey(pos);
    }

    // private static boolean isSquareUnderAttack(Color player, Piece square) {
       
    //     for (int i = 0; i < ChessBoard.length; i++) {
    //         for (int j = 0; j < ChessBoard[i].length; j++) {
    //             Piece boardSquare = ChessBoard[i][j];
    //             if (boardSquare.isOccupied() && boardSquare.getColor() != player) {
                   
    //                 ArrayList<Piece> moves = boardSquare.deepestMovesFrom(new Piece(i, j));
                   
    //                 if (moves.contains(square)) {
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }

    // private static Piece getKingSquare(Color player) {
       
    //     for (int i = 0; i < ChessBoard.length; i++) {
    //         for (int j = 0; j < ChessBoard[i].length; j++) {
    //             Piece boardSquare = ChessBoard[i][j];
    //             if (boardSquare.isOccupied() && boardSquare instanceof King && boardSquare.getColor() == player) {
                    
    //                 return new Piece(player, i, j);
    //             }
    //         }
    //     }
    //     return null; 
    // }

    // private static List<Move> getLegalMoves(Color player) {
    //     List<Move> legalMoves = new ArrayList<>();
        
    //     for (int i = 0; i < ChessBoard.length; i++) {
    //         for (int j = 0; j < ChessBoard[i].length; j++) {
    //             Piece boardSquare = ChessBoard[i][j];
    //             if (boardSquare.isOccupied() && boardSquare.piece.isBlack == (player == Color.BLACK)) {
                    
    //                 ArrayList<ChessCoordinatePair> moves = boardSquare.piece.deepestMovesFrom(new ChessCoordinatePair(i, j));
                   
    //                 for (ChessCoordinatePair move : moves) {
    //                     legalMoves.add(new Move(boardSquare.piece, new ChessCoordinatePair(i, j), move));
    //                 }
    //             }
    //         }
    //     }
    //     return legalMoves;
    // }

    public static boolean isCheckmate(Color player) {

        String kingPos = getKingPosition(player);
        Piece kingPiece = ChessBoard.get(kingPos);
        return kingPiece.isInCheckmate(kingPos);
        // if (isCheck(player) && !hasLegalMoves(player)) {
        //     return true;
        // }
    }

    public static String getKingPosition(Color player){
        for(String pos: ChessBoard.keySet()){
            Piece tempPiece = ChessBoard.get(pos);
            if (player == Color.WHITE){
                if (tempPiece instanceof King && tempPiece.getColor() == Color.WHITE){
                    return pos;
                }
            }else{ //Piece is Color.BLACK
                if (tempPiece instanceof King && tempPiece.getColor() == Color.BLACK){
                    return pos;
                }
            }
        }
        return null; //No kings in board. If this happens, game has to be restarted.
    }
    public static boolean isCheck (Color player) {
        String kingPos = getKingPosition(player);
        if (player == Color.WHITE){
            for (String pos: Board.ChessBoard.keySet()){
                Piece tempPiece = Board.ChessBoard.get(pos);
                if ((tempPiece.getColor() == Color.BLACK) && (tempPiece.ableToMove(pos, kingPos) && (tempPiece != Board.ChessBoard.get(kingPos)))){
                    return true;
                }
            }
            return false; // TEMPORARY
        }else{ //When King is Color.BLACK
            for (String pos: Board.ChessBoard.keySet()){
                Piece tempPiece = Board.ChessBoard.get(pos);
                if ((tempPiece.getColor() == Color.WHITE) && (tempPiece.ableToMove(pos, kingPos) && (tempPiece != Board.ChessBoard.get(kingPos)))){
                    return true;
                }
            }
            return false;
        }
    }

    // private static boolean hasLegalMoves(Color player) {
    //     List<Move> legalMoves = getLegalMoves(player);
    //     return !legalMoves.isEmpty();
    // }

    // private static boolean isKingUnderAttack(Color player) {
    //     Piece kingSquare = getKingSquare(player);
    //     if (player == Color.WHITE){
    //         return isSquareUnderAttack(Color.BLACK, kingSquare);
    //     }else{
    //         return isSquareUnderAttack(Color.WHITE, kingSquare);
    //     }
    // }

    public static boolean isBlankSpace(String pos){
        if((ChessBoard.get(pos) instanceof BlankSpace)){
            return true;
        }else{
            return false;
        }
    }

}