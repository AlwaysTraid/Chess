package chess;

public class King extends Piece{
    
    boolean canCastle;

    public King(Color color, int row, int col, boolean canCastle){
        super(color, row, col);
        this.canCastle = canCastle;
    }

    public boolean canCastle(){
        return this.canCastle;
    }

    public void setCastleDone(boolean setCastle){
        this.canCastle = setCastle;
    }

    public boolean ableToMove(){
        return true;
    }

    public void move(){
        
    }
    
}
