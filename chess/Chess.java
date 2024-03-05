package chess;

// Submission By: Aryan Rejo (NetID: ar1856) & Sania Kumbhare(NetID: ssk208)

import java.util.ArrayList;
import java.util.Scanner;
import chess.Board;
import chess.Color;
import chess.*;

public class Chess {
    
    enum Player { white, black }

    private PlayChess playChess;

    public Chess() {
        playChess = new PlayChess();
    }
    
    /**
     * Plays the next move for whichever player has the turn.
     * 
     * @param move String for next move, e.g. "a2 a3"
     * 
     * @return A ReturnPlay instance that contains the result of the move.
     *         See the section "The Chess class" in the assignment description for details of
     *         the contents of the returned ReturnPlay instance.
     */
    public static ReturnPlay play(String move) {
        ReturnPlay returnPlay = new ReturnPlay();
            if (Board.isCheckmate(Color.WHITE)) {
                returnPlay.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
            } else if (Board.isCheckmate(Color.BLACK)) {
                returnPlay.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
            } else if (Board.isCheck(Color.WHITE)) {
                returnPlay.message = ReturnPlay.Message.CHECK;
            } else if (Board.isCheck(Color.BLACK)) {
                returnPlay.message = ReturnPlay.Message.CHECK;
            } else {
                returnPlay.message = ReturnPlay.Message.LEGAL_MOVE;
            }
        
            
        
            return returnPlay;
        }


    /**
     * This method should reset the game, and start from scratch.
     */
    public static void start() {
        Board.setupBoard();
        Scanner input = new Scanner(System.in);
        String response;

        while (true) {
            if (Board.GetMove() == Color.WHITE) {
                System.out.print("White To Move: ");
                response = input.nextLine();
            } else {
                System.out.print("Black To Move: ");
                response = input.nextLine();
            }

            if (response.equals("draw")) {
                if (Board.GetMove() == Color.WHITE) {
                    System.out.println("Draw Was Offered By White.");
                    System.out.println("Draw was Accepted By Black.");
                } else {
                    System.out.println("Draw Was Offered By Black.");
                    System.out.println("Draw was Accepted By White.");
                }
                break; // Exit the loop if draw is accepted
            } else if (response.equals("resign")) {
                if (Board.GetMove() == Color.WHITE) {
                    System.out.println("White Resigns. Black Wins!");
                } else {
                    System.out.println("Black Resigns. White Wins!");
                }
                break; // Exit the loop if resignation occurs
            } else {
                // Handle other moves
            }
        }
    }
}
