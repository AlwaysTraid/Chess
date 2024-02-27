package chess;
import chess.*;

public class Pawn extends Piece{
    boolean hasPassantAvailable = true;

    public Pawn(String display, Color color, int row, int col, boolean hasPassantAvailable){
        super(display, color, row, col);
        this.hasPassantAvailable = hasPassantAvailable;
    }

    public void setPassant(boolean setPassant){
        this.hasPassantAvailable = setPassant;
    }

    public boolean canPassant(){
        return this.hasPassantAvailable;
    }

    public boolean ableToMove(String oldPos, String newPos){
        if(Board.GetPiece(oldPos).getColor() == Color.WHITE){
            if(oldPos.charAt(1) >= newPos.charAt(1)){
                return false;
            }else{
                if (newPos.charAt(1) - oldPos.charAt(1) != 1){
                    return false;
                }
                if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                    return false;
                }
                if (canTravel(oldPos, newPos))
                    return true;
                else
                    return false;
            }
        }else{
            //Piece is Color.BLACK
            if (oldPos.charAt(1) <= newPos.charAt(1)){
                return false;
            }
            else{
                if (oldPos.charAt(1) - newPos.charAt(1) != 1){
                    return false;
                }
                if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                    return false;
                }
                if (canTravel(oldPos, newPos))
                    return true;
                else
                    return false;
            }
        }
    }

    public void move(String oldPos, String newPos){
        
    }
}