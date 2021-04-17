import java.awt.*;

public class LinesOfAction {

    public static void main(String[] args){

        Pair[] COMPASS = {
                new Pair(-1, 0), // North
                new Pair(-1, 1), // Northeast
                new Pair(0, 1), // East
                new Pair(1, 1), // Southeast
                new Pair(1, 0), // South
                new Pair(1, -1), // Southwest
                new Pair(0, -1), // West
                new Pair(-1, -1), // Northwest
        };

        boolean count = false; // false represents black's turn. true represents white's turn.
        Board board = new Board();

        StdDraw.setScale(-0.5, board.status.length - 0.5);
        StdDraw.enableDoubleBuffering();

        drawGrid(board);

        while (true) {
            handleMouseClick(board, count, COMPASS);
            drawGrid(board);
            if (hasWon(board, count, COMPASS)) {
                StdOut.println("You won!");
                break;
            }
            if (count) count = false;
            else count = true;
        }

    }

    public static boolean hasWon(Board board, boolean count, Pair[] COMPASS) {

        boolean[][] gridConnected = new boolean[8][8];
        Pair start = board.startPair(count);
        assert start != null;

        return (board.isConnected(gridConnected, COMPASS, start) == board.numCheckers(count));


    }

    public static void handleMouseClick(Board board, boolean count, Pair[] COMPASS) {
        int countInt;

        if (count) {
            countInt = 1;
            StdOut.println("White's move.");
        } else {
            countInt = 2;
            StdOut.println("Black's move.");
        }

        while (!StdDraw.isMousePressed()) {
            // Empty loop.
        }

        Pair p1 = new Pair((int)Math.round(StdDraw.mouseX()), (int)Math.round(StdDraw.mouseY()));

        while (StdDraw.isMousePressed()) {
            // do nothing
        }
        if (board.status[p1.column][p1.row] != countInt) {
            while (true) {
                highlightValidMoves(board, p1, COMPASS, count);

                while (!StdDraw.isMousePressed()) {
                    // do nothing
                }

                Pair p2 = new Pair((int) Math.round(StdDraw.mouseX()), (int) Math.round(StdDraw.mouseY()));

                while (StdDraw.isMousePressed()) {
                    // do nothing
                }
                if (p2.equals(p1)) {
                    drawGrid(board);
                    handleMouseClick(board, count, COMPASS);

                    break;
                } else {
                    if (!highlightValidMoves(board, p1, COMPASS, count)[p2.column][p2.row]) {
                        StdOut.println("Invalid move.");
                    } else {
                        board = board.movePiece(p1, p2);
                        break;
                    }
                }
            }
        } else {
            handleMouseClick(board, count, COMPASS);
        }

    }

    public static boolean[][] highlightValidMoves(Board board, Pair p1, Pair[] COMPASS, boolean count) { //Buggy

        boolean[][] validMoves = new boolean[8][8];

        StdDraw.clear();
        drawBackground();

        for (Pair direction : COMPASS) {

            boolean valid = true;
            int numCheckers = board.numCheckers(p1, direction);
            Pair temp = p1;
            int us = board.status[p1.column][p1.row];
            int them = flipChecker(us);


            for (int i = 0; i<numCheckers; i++) {
                temp = temp.addPair(direction);
                if (!temp.isValid()) {
                    valid = false;
                    break;
                }
                if (i != numCheckers - 1 && board.status[temp.column][temp.row] == them) {
                    valid = false;
                    break;
                }
                if (i == numCheckers - 1 && board.status[temp.column][temp.row] == us) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                validMoves[temp.column][temp.row] = true;

                StdDraw.setPenColor(Color.darkGray);
                StdDraw.filledSquare(temp.column, temp.row, 0.5);

            }

        }

        drawBlankGrid(board);

        return validMoves;
    }

    public static int flipChecker(int color) {
        if (color == 1) return 2;
        if (color == 2) return 1;
        else return 0;
    }

    public static void drawGrid(Board board) {
        StdDraw.clear();
        StdDraw.setPenColor(Color.lightGray);
        StdDraw.filledSquare(3.5,3.5,4);
        for (int i=0; i<9; i++) {
            StdDraw.setPenColor(Color.black);
            StdDraw.line(-0.5+i, -0.5, -0.5+i, 7.5);
            StdDraw.line(7.5,-0.5+i, -0.5, -0.5+i);
        }
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++){
                if (board.status[i][j] == 1) {
                    StdDraw.setPenColor(Color.black);
                    StdDraw.filledCircle((double) i,(double) j,0.4);
                }
                if (board.status[i][j] == 2) {
                    StdDraw.setPenColor(Color.white);
                    StdDraw.filledCircle((double) i,(double) j,0.4);
                }
            }
        }
        StdDraw.show();
    }

    public static void drawBackground() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.lightGray);
        StdDraw.filledSquare(3.5,3.5,4);
    }

    public static void drawBlankGrid(Board board) {
        for (int i=0; i<9; i++) {
            StdDraw.setPenColor(Color.black);
            StdDraw.line(-0.5+i, -0.5, -0.5+i, 7.5);
            StdDraw.line(7.5,-0.5+i, -0.5, -0.5+i);
        }
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++){
                if (board.status[i][j] == 1) {
                    StdDraw.setPenColor(Color.black);
                    StdDraw.filledCircle((double) i,(double) j,0.4);
                }
                if (board.status[i][j] == 2) {
                    StdDraw.setPenColor(Color.white);
                    StdDraw.filledCircle((double) i,(double) j,0.4);
                }
            }
        }
        StdDraw.show();
    }

}
