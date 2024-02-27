package chess;

public class BlankSpace extends Piece{
    
    public BlankSpace(String display, Color color, int row, int col){
        super(display, color, row, col);
    }

    @Override
    public boolean ableToMove(String oldPos, String newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ableToMove'");
    }

    @Override
    public void move(String oldPos, String newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean canTravel(String oldPos, String newPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canTravel'");
    }

    @Override
    public boolean isOccupied(){
        return false;
    }
}
