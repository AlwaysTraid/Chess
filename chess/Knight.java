package chess;
import java.util.ArrayList;
import chess.*;

public class Knight extends Piece{

    public Knight(Color color, int row, int col) {
        super(color, row, col);
    }

    public boolean ableToMove(String oldPos, String newPos){
        ArrayList<String> possibleMoves = new ArrayList<String>();

        String topleftbottom = Character.toString((char)(oldPos.charAt(0) - 2)) + (char)(((oldPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(topleftbottom);
		String toplefttop = Character.toString((char)(oldPos.charAt(0) - 1)) + (char)(((oldPos.charAt(1)-'0')+2)+'0');
        possibleMoves.add(toplefttop);

		String toprightbottom = Character.toString((char)(oldPos.charAt(0) + 2)) + (char)(((oldPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(toprightbottom);
		String toprighttop = Character.toString((char)(oldPos.charAt(0) + 1)) + (char)(((oldPos.charAt(1)-'0')+2)+'0');
        possibleMoves.add(toprighttop);
		
		String bottomleftbottom = Character.toString((char)(oldPos.charAt(0) - 1)) + (char)(((oldPos.charAt(1)-'0')-2)+'0');
        possibleMoves.add(bottomleftbottom);
		String bottomlefttop = Character.toString((char)(oldPos.charAt(0) - 2)) + (char)(((oldPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomlefttop);

		String bottomrightbottom = Character.toString((char)(oldPos.charAt(0) + 1)) + (char)(((oldPos.charAt(1)-'0')-2)+'0');
        possibleMoves.add(bottomrightbottom);
		String bottomrighttop = Character.toString((char)(oldPos.charAt(0) + 2)) + (char)(((oldPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomrighttop);

        for (String move : possibleMoves){
            if (Board.ExistingSpotOnBoard(move)){
                if ((Board.GetPiece(newPos) instanceof BlankSpace) && (Board.GetPiece(oldPos).getColor() != Board.GetPiece(newPos).getColor())){
                    return true;
                }
            }
        }
        return false;
    }

    public void move(String oldPos, String newPos){
        
    }
    
}
