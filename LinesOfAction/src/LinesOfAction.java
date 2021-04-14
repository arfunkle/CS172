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
        Pair start = startPair(board, count);

        return (isConnected(board, gridConnected, count, COMPASS, start)==numCheckers(board, count));


    }

    public static int numCheckers(Board board, boolean count) {
        int numCheckers = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!count) if (board.status[i][j]==1) {
                    numCheckers++;
                } if (count) if (board.status[i][j]==2) {
                    numCheckers++;
                }
            }
        }
        StdOut.println(numCheckers);
        return numCheckers;
    }


    public static Board movePiece(Board board, Pair p1, Pair p2) {
        board.status[p2.column][p2.row] = board.status[p1.column][p1.row];
        board.status[p1.column][p1.row] = 0;
        return board;
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
                        movePiece(board, p1, p2);
                        break;
                    }
                }
            }
        } else {
            handleMouseClick(board, count, COMPASS);
        }

    }

    public static boolean[][] highlightValidMoves(Board board, Pair p1, Pair[] COMPASS, boolean count) {

        boolean[][] validMoves = new boolean[8][8];

        StdDraw.clear();
        drawBackground(board);

        for (Pair direction : COMPASS) {

            boolean valid = true;
            int numCheckers = 1;
            Pair temp = p1;

            while ((temp.column >= 0) && (temp.row >= 0) && (temp.column < 8) && (temp.row < 8)) {
                if (board.status[temp.column][temp.row] > 0) numCheckers++;

                temp = temp.addPair(direction);
            }
            temp = p1;

            for (int i = 1; i<numCheckers; i++) {

                if (!count) {
                    if (board.status[temp.column][temp.row] == 2) valid = false;
                } else {
                    if (board.status[temp.column][temp.row] == 1) valid = false;
                }
                temp = temp.addPair(direction);
                if (!((temp.column >= 0) && (temp.row >= 0) && (temp.column < 8) && (temp.row < 8))) {
                    temp = temp.addPair(direction.flipDirection());
                }

            }


            if (board.status[temp.column][temp.row] == board.status[p1.column][p1.row]) valid = false;

            if (valid) {
                validMoves[temp.column][temp.row] = true;

                StdDraw.setPenColor(Color.darkGray);
                StdDraw.filledSquare(temp.column, temp.row, 0.5);

            }

        }

        drawBlankGrid(board);

        return validMoves;
    }

    public static Pair startPair(Board board, boolean count) {
        int temp = 0;
        for (int[] i : board.status) {
            for (int j : i) {
                if (!count) if (i[j]==1) {
                    return new Pair(temp, j);

                } if (count) if (i[j]==2) {
                    return new Pair(temp, j);

                }
            }
            temp++;
        }
        return null;
    }

    public static int isConnected(Board board, boolean[][] gridConnected, boolean count, Pair[] COMPASS, Pair start) {
        // false = black's turn

        int countInt = 0;
        if (count) countInt++;
        else countInt += 2;

        int numCheckers = 0;

        for (Pair direction : COMPASS) {
            if ((start.addPair(direction).column >= 0) && (start.addPair(direction).column < 8) &&
                    (start.addPair(direction).row >= 0) && (start.addPair(direction).row < 8)) {

                if ((board.status[start.addPair(direction).column][start.addPair(direction).row] == countInt) &&
                        (!gridConnected[start.addPair(direction).column][start.addPair(direction).row])) {

                    gridConnected[start.addPair(direction).column][start.addPair(direction).row] = true;
                    numCheckers = 1 + isConnected(board, gridConnected, count, COMPASS, start.addPair(direction));
                }
            }
        }
        StdOut.println(numCheckers);
        return numCheckers;
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

    public static void drawBackground(Board board) {

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
