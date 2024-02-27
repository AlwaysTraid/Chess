package chess;

import chess.*;

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
        (oldPos.charAt(1) <= newPos.charAt(1)) ||
        (oldPos.charAt(1) >= newPos.charAt(1))
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
        
    }
    
}
