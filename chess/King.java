package chess;

import chess.*;

import java.util.ArrayList;

public class King extends Piece{
    
    boolean canCastle;

    public King(String display, Color color, int row, int col, boolean canCastle){
        super(display, color, row, col);
        this.canCastle = canCastle;
    }

    public boolean canCastle(){
        return this.canCastle;
    }

    public void setCastleDone(boolean setCastle){
        this.canCastle = setCastle;
    }

    public boolean ableToMove(String oldPos, String newPos){
        if (
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 0 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 1 )||
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 1 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 0 ) ||
        ( (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == 1 && (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) == 1 && (Math.abs(oldPos.charAt(0) - newPos.charAt(0))) == (Math.abs( oldPos.charAt(1) - newPos.charAt(1))) ) 
        ){
            if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                return false;
            } 
            return true;
        }else{
            return false;
        }
        
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldKingPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldKingPiece); // Moves King To New Spot
        
        //Change to either White/Black BlankSpace Piece
        if(Board.getSquareColor(file, rank) == Color.WHITE){
            Board.ChessBoard.put(oldPos, new BlankSpace("  ", Color.WHITE, file, rank));
        }else{
            Board.ChessBoard.put(oldPos, new BlankSpace("##", Color.BLACK, file, rank));
        }
    }

    public boolean isInCheckmate(String currentPos){

        ArrayList<String> possibleMoves = new ArrayList<String>();

        String topleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(topleft);
        String top = Character.toString((char)(currentPos.charAt(0) + 0)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(top);
        String topright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')+1)+'0');
        possibleMoves.add(topright);

        String middleleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')+0)+'0');
        possibleMoves.add(middleleft);
        String middleright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')+0)+'0');
        possibleMoves.add(middleright);

        String bottomleft = Character.toString((char)(currentPos.charAt(0) - 1)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomleft);
        String bottommiddle = Character.toString((char)(currentPos.charAt(0) + 0)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottommiddle);
        String bottomright = Character.toString((char)(currentPos.charAt(0) + 1)) + (char)(((currentPos.charAt(1)-'0')-1)+'0');
        possibleMoves.add(bottomright);

        for (String move : possibleMoves){
            if (!Board.ExistingSpotOnBoard(move)) { // Move doesn't exist on Board
                possibleMoves.remove(move);
            }
            if (Board.ChessBoard.get(move).getColor() == color){ // Piece exists, but is same color as King
                possibleMoves.remove(move);
            }
        }

        // We Now Use Possible Moves And Use Board Function isCheck at each move
        Board.ChessBoard.put(currentPos, new BlankSpace("  ", Color.WHITE, currentPos.charAt(0), currentPos.charAt(1))); //Temp Placement Of New Spot for IsCheck
        if (color == Color.WHITE){
            for (String pos: possibleMoves){
                Piece tempPiece = Board.ChessBoard.get(pos);
                if ((tempPiece.getColor() == Color.BLACK) && !(tempPiece instanceof BlankSpace)){ //If There IS a Piece on this spot and not a blank spot
                    //Board.ChessBoard.put(pos, new BlankSpace("  ", Color.WHITE, pos.charAt(0), pos.charAt(1)));
                    Board.ChessBoard.put(pos, this); //Place King In New Spot
                    if(!Board.isCheck(color)){
                        Board.ChessBoard.put(currentPos, this);
                        Board.ChessBoard.put(pos, tempPiece);
                        return false; // Able to move, not checkmate.
                    }
                    Board.ChessBoard.put(pos, tempPiece); // Reset, but IS still in check here
                }

                Board.ChessBoard.put(pos, this);
                if (!(Board.isCheck(color))){
                    Board.ChessBoard.put(currentPos, this);
                    Board.ChessBoard.put(pos, tempPiece);
                    return false; // Not in check at this location
                }

                //Reset In Case Of Both Cases Not Fulfilling
                Board.ChessBoard.put(pos, tempPiece);
            }
        }else{ //When King is Color.BLACK
            for (String pos: possibleMoves){
                Piece tempPiece = Board.ChessBoard.get(pos);
                if ((tempPiece.getColor() == Color.WHITE) && !(tempPiece instanceof BlankSpace)){ //If There IS a Piece on this spot and not a blank spot
                    //Board.ChessBoard.put(pos, new BlankSpace("  ", Color.WHITE, pos.charAt(0), pos.charAt(1)));
                    Board.ChessBoard.put(pos, this); //Place King In New Spot
                    if(!Board.isCheck(color)){
                        Board.ChessBoard.put(currentPos, this);
                        Board.ChessBoard.put(pos, tempPiece);
                        return false; // Able to move, not checkmate.
                    }
                    Board.ChessBoard.put(pos, tempPiece); // Reset, but IS still in check here
                }

                Board.ChessBoard.put(pos, this);
                if (!(Board.isCheck(color))){
                    Board.ChessBoard.put(currentPos, this);
                    Board.ChessBoard.put(pos, tempPiece);
                    return false; // Not in check at this location
                }

                //Reset In Case Of Both Cases Not Fulfilling
                Board.ChessBoard.put(pos, tempPiece);
            }
        }


        return true; //All tests have been made for white and black, no possible escape squares so it IS checkmate.
    }
    
}
