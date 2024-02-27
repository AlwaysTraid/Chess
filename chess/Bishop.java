package chess;

import chess.*;

public class Bishop extends Piece{

    public Bishop(String display, Color color, int row, int col){
        super(display, color, row, col);
    }

    public boolean ableToMove(String oldPos, String newPos){
        if (
        (Math.abs(oldPos.charAt(0) - newPos.charAt(0)) == Math.abs(oldPos.charAt(1) - newPos.charAt(1))) ||
        (newPos.equals(oldPos))
        ){
            if (Board.GetPiece(newPos) instanceof BlankSpace){
                if (canTravel(oldPos, newPos)){
                    return true;
                }
                else{
                    return false;
                }
            }else {
                if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                    return false;
                }
                else if (canTravel(oldPos, newPos)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }
    
    public boolean canTravel(String oldPos, String newPos){
        int filediff = newPos.charAt(0) - oldPos.charAt(0);
        int rankdiff = newPos.charAt(1) - oldPos.charAt(1);

        int fileTravelingRight = filediff > 0 ? 1 : -1;
        int rankTravelingRight = rankdiff > 0 ? 1 : -1;

        int file = oldPos.charAt(0) + fileTravelingRight;
        int rank = oldPos.charAt(1) + rankTravelingRight;

        while(file != newPos.charAt(0) && rank != newPos.charAt(1)){
            char charfile = (char) file;
            String pos = Character.toString(charfile) + Integer.toString(rank);
            if(!((Board.ChessBoard.get(pos)).getDisplay().equals("  ") || (Board.ChessBoard.get(pos)).getDisplay().equals("##"))){
                return false;
            }
        }
        return true;
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldBishopPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldBishopPiece); // Moves Bishop To New Spot
        
        //Change to either White/Black BlankSpace Piece
        if(Board.getSquareColor(file, rank) == Color.WHITE){
            Board.ChessBoard.put(oldPos, new BlankSpace("  ", Color.WHITE, file, rank));
        }else{
            Board.ChessBoard.put(oldPos, new BlankSpace("##", Color.BLACK, file, rank));
        }
    }
}
