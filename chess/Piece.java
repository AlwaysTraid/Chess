package chess;
import chess.*;
import java.util.*;

//Defines All Pieces In Chess

public class Piece extends ReturnPiece{
    
    Color color;
    String display;
    int row;
    int col;
    boolean moved;

    public Piece(String display, Color color, int row, int col){
        this.display = display;
        this.color = color;
        this.row = row;
        this.col = col;
    }
    
    public String getDisplay(){
        return this.display;
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

    public boolean isOccupied(){
        return true;
    }

    public List<String> deepestMovesFrom(Piece pos){
        return new ArrayList<String>();
    }
}
