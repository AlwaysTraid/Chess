package chess;

import chess.*;

public class Queen extends Piece{

    public Queen(String display, Color color, int row, int col){
        super(display, color, row, col);
    }

    public boolean ableToMove(String oldPos, String newPos){
        //Check If Move Is Valid For Queen
        if (
            (oldPos.charAt(0) == newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1)) ||
            (Math.abs(oldPos.charAt(0) - newPos.charAt(0)) == Math.abs(oldPos.charAt(1) - newPos.charAt(1))) ||
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

        //Rook Check
        if ((file1 == file2) || rank1 == rank2){
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
        //Bishop Check
        else if ((Math.abs(file1 - file2) == Math.abs(rank1 - rank2))){
            int filediff = newPos.charAt(0) - oldPos.charAt(0);
            int rankdiff = newPos.charAt(1) - oldPos.charAt(1);

            int fileTravelingRight = filediff > 0 ? 1 : -1;
            int rankTravelingRight = rankdiff > 0 ? 1 : -1;

            int file = oldPos.charAt(0) + fileTravelingRight;
            int rank = oldPos.charAt(1) + rankTravelingRight;

            while(file != newPos.charAt(0) && rank != newPos.charAt(1)){
                char charfile = (char) file;
                String pos = Character.toString(charfile) + Integer.toString(rank);
                if(!((Board.ChessBoard.get(pos)).getDisplay().equals("  ") || (Board.ChessBoard.get(pos)).getDisplay().equals("##"))){
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldQueenPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldQueenPiece); // Moves Queen To New Spot
        
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
