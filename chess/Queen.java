package chess;

import chess.*;

public class Queen extends Piece{

    public Queen(String display, Color color, int row, int col){
        super(display, color, row, col);
    }

    public boolean ableToMove(String oldPos, String newPos){
        //Check If Move Is Valid For Queen
        if (
            (oldPos.charAt(0) == newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1)) ||
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

    public void move(String oldPos, String newPos){
        
    }

}
