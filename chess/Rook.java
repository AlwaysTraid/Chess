package chess;

import chess.*;
import java.util.*;

public class Rook extends Piece
{
    public Rook(String display, Color color, int row, int col){
        super(display, color, row, col);
    }


    public boolean ableToMove(String oldPos, String newPos){

        if (!Board.ExistingSpotOnBoard(newPos)){
            System.out.println("Spot Doesn't Exist");
            return false;
        }

        if (
            (oldPos.charAt(0) == newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1)) ||
            (newPos.equals(oldPos))
        ){
            if (Board.GetPiece(newPos) instanceof BlankSpace){
                if (canTravel(oldPos, newPos)){
                    return true;
                }
                else{
                    return false;
                }
            }else {
                if (Board.GetPiece(oldPos).getColor() == Board.GetPiece(newPos).getColor()){
                    return false;
                }
                else if (canTravel(oldPos, newPos)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }

    public boolean canTravel(String oldPos, String newPos){
        char file1 = oldPos.charAt(0);
        char file2 = newPos.charAt(0);

        int rank1 = oldPos.charAt(1);
        int rank2 = newPos.charAt(1);

        //Check If Traveling Along Same File
        if (file1 == file2){
            if (rank1 > rank2){
                for (int i = rank2+1; i < rank1; i++){
                    if(!isOccupied(file1, i)){
                        return false;
                    }
                }
            } //rank2 > rank1 - Can't be same as this is already checked earlier
            else{
                for (int i = rank1+1; i < rank2; i++){
                    if (!isOccupied(file1, i)){
                        return false;
                    }
                }
            }
        }
        //Traveling Along The Same Rank (rank1 == rank2)
        else{
            if (file1 > file2){
                for (char i = (char)(rank2+1); i < file1; i++){
                    if(!isOccupied(i, rank1)){
                        return false;
                    }
                }
            }
            //file2 > file1
            else{
                for (char i = (char)(rank1+1); i < file2; i++){
                    if (!isOccupied(i, rank1)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldRookPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldRookPiece); // Moves Rook To New Spot
        
        //Change to either White/Black BlankSpace Piece
        if(Board.getSquareColor(file, rank) == Color.WHITE){
            Board.ChessBoard.put(oldPos, new BlankSpace("  ", Color.WHITE, file, rank));
        }else{
            Board.ChessBoard.put(oldPos, new BlankSpace("##", Color.BLACK, file, rank));
        }
    }

    public boolean isOccupied(char file, int rank){
        String position = Character.toString(file) + Integer.toString(rank);
        String display = Board.ChessBoard.get(position).getDisplay();
        if (display == "  " || display == "##"){
            return true;
        }else{
            return false;
        }
    }
    
}
