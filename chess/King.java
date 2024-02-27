package chess;

import chess.*;

import java.util.ArrayList;

public class King extends Piece{
    
    boolean canCastle;

    public King(String display, Color color, int row, int col, boolean canCastle){
        super(display, color, row, col);
        this.canCastle = canCastle;
    }

    public boolean canCastle(){
        return this.canCastle;
    }

    public void setCastleDone(boolean setCastle){
        this.canCastle = setCastle;
    }

    public boolean ableToMove(String oldPos, String newPos){
        if (
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 0 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 1 )||
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 1 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 0 ) ||
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 1 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 1 && (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) ) 
        ){
            if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                return false;
            } 
            return true;
        }else{
            return false;
        }
        
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldKingPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldKingPiece); // Moves King To New Spot
        
        //Change to either White/Black BlankSpace Piece
        if(Board.getSquareColor(file, rank) == Color.WHITE){
            Board.ChessBoard.put(oldPos, new BlankSpace("  ", Color.WHITE, file, rank));
        }else{
            Board.ChessBoard.put(oldPos, new BlankSpace("##", Color.BLACK, file, rank));
        }
    }

    public boolean isInCheckmate(String currentPos){

        ArrayList<String> possibleMoves = new ArrayList<String>();

        String topleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(topleft);
        String top = Character.toString((char)(currentPos.charAt(0) + 0)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(top);
        String topright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(topright);

        String middleleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')+0)+'0');
        possibleMoves.add(middleleft);
        String middleright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')+0)+'0');
        possibleMoves.add(middleright);

        String bottomleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomleft);
        String bottommiddle = Character.toString((char)(currentPos.charAt(0) + 0)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottommiddle);
        String bottomright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomright);

        for (String move : possibleMoves){
            if (!Board.ExistingSpotOnBoard(move)) { // Move doesn't exist on Board
                possibleMoves.remove(move);
            }
            if (Board.ChessBoard.get(move).getColor() == color){ // Piece exists, but is same color as King
                possibleMoves.remove(move);
            }
        }

        

        return false;
    }
    
}
