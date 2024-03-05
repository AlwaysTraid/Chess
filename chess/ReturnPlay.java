package chess;

import java.util.ArrayList;

public class ReturnPlay {

    public static final String Message = null;
    public Object message;
    public ArrayList<ReturnPiece> piecesOnBoard;

        public enum Message {
            CHECKMATE_WHITE_WINS,
            CHECKMATE_BLACK_WINS,
            CHECK_WHITE,
            CHECK_BLACK,
            LEGAL_MOVE
        }
    }
    

