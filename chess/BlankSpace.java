package chess;

public class BlankSpace extends Piece{
    
    public BlankSpace(Color color, int row, int col){
        super(color, row, col);
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
}
