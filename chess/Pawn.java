package chess;

public class Pawn extends Piece{
    boolean hasPassantAvailable = true;

    public Pawn(Color color, int row, int col, boolean hasPassantAvailable){
        super(color, row, col);
        this.hasPassantAvailable = hasPassantAvailable;
    }

    public void setPassant(boolean setPassant){
        this.hasPassantAvailable = setPassant;
    }

    public boolean canPassant(){
        return this.hasPassantAvailable;
    }

    public boolean ableToMove(){
        return true;
    }

    public void move(){
        
    }
}
