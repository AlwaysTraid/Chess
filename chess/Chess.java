package chess;

// Submission By: Aryan Rejo (NetID: ar1856) & Sania Kumbhare(NetID: ssk208)

import java.util.Scanner;
import java.util.ArrayList;
import chess.Board.Color;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {

    /**
     * Plays the next move for whichever player has the turn.
     *
     * @param move String for next move, e.g. "a2 a3"
     * @return A ReturnPlay instance that contains the result of the move.
     * See the section "The Chess class" in the assignment description for details of
     * the contents of the returned ReturnPlay instance.
     */
    public static ReturnPlay play(String move) {
        ReturnPlay playResult = new ReturnPlay();
        // Parse the move and apply it to the board
        // For simplicity, let's assume move parsing and application is done here
        // Assuming the move is applied, then check for checkmate and check

        if (Board.isCheckmate(Color.WHITE)) {
            playResult.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
        } else if (Board.isCheckmate(Color.BLACK)) {
            playResult.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
        } else if (Board.isCheck(Color.WHITE)) {
            playResult.message = ReturnPlay.Message.CHECK;
        } else if (Board.isCheck(Color.BLACK)) {
            playResult.message = ReturnPlay.Message.CHECK;
        } else {
            playResult.message = ReturnPlay.Message.LEGAL_MOVE;
        }
        return playResult;
    }

    /**
     * This method should reset the game, and start from scratch.
     */
    public static void start() {
        Board.setupBoard();
        Scanner input = new Scanner(System.in);
        String response;

        while (true) {
            if (Board.getMove() == Color.WHITE) {
                System.out.print("White To Move: ");
                response = input.nextLine();
            } else {
                System.out.print("Black To Move: ");
                response = input.nextLine();
            }

            if (response.equals("draw")) {
                if (Board.getMove() == Color.WHITE) {
                    System.out.println("Draw Was Offered By White.");
                    System.out.println("Draw was Accepted By Black.");
                } else {
                    System.out.println("Draw Was Offered By Black.");
                    System.out.println("Draw was Accepted By White.");
                }
            } else if (response.equals("resign")) {
                if (Board.getMove() == Color.WHITE) {
                    System.out.println("White Resigns. Black Wins!");
                } else {
                    System.out.println("Black Resigns. White Wins!");
                }
            }
        }
    }
}
