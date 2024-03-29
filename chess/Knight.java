package chess;
import java.util.ArrayList;
import chess.*;

public class Knight extends Piece{

    public Knight(String display, Color color, int row, int col) {
        super(display, color, row, col);
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
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldKnightPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldKnightPiece); // Moves Knight To New Spot
        
        //Change to either White/Black BlankSpace Piece
        if(Board.getSquareColor(file, rank) == Color.WHITE){
            Board.ChessBoard.put(oldPos, new BlankSpace("  ", Color.WHITE, file, rank));
        }else{
            Board.ChessBoard.put(oldPos, new BlankSpace("##", Color.BLACK, file, rank));
        }
    }
    
}
