package chess;

public abstract class Piece {
    
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

    public abstract boolean ableToMove(/*Spot, Declared Spot */);
    public abstract void move(/*Spot, Declared Spot */);
}
