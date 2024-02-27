package chess;
import chess.*;

public class Pawn extends Piece{
    boolean hasPassantAvailable = true;

    public Pawn(String display, Color color, int row, int col, boolean hasPassantAvailable){
        super(display, color, row, col);
        this.hasPassantAvailable = hasPassantAvailable;
    }

    public void setPassant(boolean setPassant){
        this.hasPassantAvailable = setPassant;
    }

    public boolean canPassant(){
        return this.hasPassantAvailable;
    }

    public boolean ableToMove(String oldPos, String newPos){

        char file1 = oldPos.charAt(0);
        char file2 = newPos.charAt(0);
        int rank1 = oldPos.charAt(1);
        int rank2 = oldPos.charAt(1);

        if(Board.GetPiece(oldPos).getColor() == Color.WHITE){ //White Pawns
            if(rank1 >= rank2){
                //Backwards
                return false;
            }else{
                //First Check - Check If Pawn Moved 2 Pieces
                if(Math.abs(rank2-rank1) == 2){
                    if(rank1 != 2){ //Not at WHITE PAWN starting rank
                        return false;
                    }
                    if(!canTravel(oldPos, newPos)){ //Something is blocking it
                        return false;
                    }
                    setPassant(false); // Can No Longer En Passant
                    return true;
                }

                //Second Check - If Capturing, If Not, Then Normal Move
                if(file1 != file2){
                    if((Math.abs(file2 - file1) != 1) || (rank2-rank1 != 1) ){
                        return false; // Pawn tried moving more than 1 space
                    }
                    if(!isOccupied(file2, rank2)){
                        return false; // Pawn tried taking an empty square
                    }
                    String pos = Character.toString(file2) + Integer.toString(rank2);
                    if(Board.ChessBoard.get(pos).getColor() == Color.WHITE){
                        return false; // Tried Capturing Same Piece
                    }
                    return true;
                }

                if (Math.abs(file2 - file1) == 1){
                    if(!canTravel(oldPos, newPos)){
                        return false;
                    }
                    return true;
                }

                return false;
            }
        }else{
            //Piece is Color.BLACK // Black Pawns
            if(rank2 >= rank1){
                //Backwards
                return false;
            }else{
                //First Check - Check If Pawn Moved 2 Pieces
                if(Math.abs(rank1-rank2) == 2){
                    if(rank1 != 7){ //Not at BLACK PAWN starting rank
                        return false;
                    }
                    if(!canTravel(oldPos, newPos)){ //Something is blocking it
                        return false;
                    }
                    setPassant(false); // Can No Longer En Passant
                    return true;
                }

                //Second Check - If Capturing, If Not, Then Normal Move
                if(file1 != file2){
                    if((Math.abs(file1 - file2) != 1) || (rank2-rank1 != 1) ){
                        return false; // Pawn tried moving more than 1 space
                    }
                    if(!isOccupied(file2, rank2)){
                        return false; // Pawn tried taking an empty square
                    }
                    String pos = Character.toString(file2) + Integer.toString(rank2);
                    if(Board.ChessBoard.get(pos).getColor() == Color.BLACK){
                        return false; // Tried Capturing Same Piece
                    }
                    return true;
                }

                if (Math.abs(file2 - file1) == 1){
                    if(!canTravel(oldPos, newPos)){
                        return false;
                    }
                    return true;
                }

                return false;
            }
            
        }
    }

    public boolean canTravel(String oldPos, String newPos){

        char file1 = oldPos.charAt(0);
        char file2 = newPos.charAt(0);
        int rank1 = oldPos.charAt(1);
        int rank2 = oldPos.charAt(1);

        if (rank1 < rank2){ // White Pawns
            for (int i = rank1+1; i <= rank2; i++){
                if(isOccupied(file1, i)){
                    return false;
                }
            }
        }else{ // Black Pawns
            for (int i = rank2; i <= rank1; i++){
                if(isOccupied(file1, i)){
                    return false;
                }
            }
        }

        return true;
    }

    public void move(String oldPos, String newPos){
        char file = oldPos.charAt(0);
        int rank = oldPos.charAt(1);
        Piece oldPawnPiece = Board.ChessBoard.get(oldPos);
        Board.ChessBoard.put(newPos, oldPawnPiece); // Moves Pawn To New Spot
        
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