package chess;
import chess.*;

//Defines All Pieces In Chess

public class Piece {
    
    Color color;
    int row;
    int col;
    boolean moved;

    public Piece(Color color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }
    
    public Color getColor(){
        return this.color;
    }

    public boolean checkIfMoved(){
        return this.moved;
    }

    public void setMoved(boolean hasMoved){
        this.moved = hasMoved;
    }

    public boolean ableToMove(String oldPos, String newPos){
        return true;
    }
    public void move(String oldPos, String newPos){

    }
    public boolean canTravel(String oldPos, String newPos){
        return true;
    }
}
