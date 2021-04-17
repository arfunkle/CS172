public class Board {

    public int[][] status = new int[8][8];

    /**
     * Board initializes the board to the starting locations.
     */
    public Board() {
        for (int i=0; i<6; i++) {
            status[i+1][0] = 1;
            status[i+1][7] = 1;
            status[0][i+1] = 2;
            status[7][i+1] = 2; // 1 is black, 2 is white.

        }
    }

    /**
     * Board with parameters initializes the board to the 2-dimensional integer array given.
     * @param board This object represents the pieces on the board.
     */
    public Board(int[][] board) {
        status = board;
    }

    /**
     * equals checks to see if the board's values are equal to the integer array given
     * @param that This object is a two-dimensional integer array.
     * @return The method returns true if all values are the same.
     */
    public boolean equals(int[][] that) {
        boolean valid = true;
        for (int c = 0; c<8; c++) {
            for (int r = 0; r<8; r++) {
                if (this.status[c][r] != that[c][r]) valid = false;
            }
        }
        return valid;
    }

    /**
     * startPair looks for the first checker of the player whose turn it is.
     * @param count This object counts the turn.
     * @return The method returns the first Pair which matches the description.
     */
    public Pair startPair(boolean count) {

        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                if (!count) if (this.status[c][r]==1) return new Pair(c, r);
                if (count) if (this.status[c][r]==2) return new Pair(c, r);
            }

        }
        return null;
    }

    /**
     * movePiece moves the value at the Pair p1 to the value at the Pair p2.
     * @param p1 This is the first Pair.
     * @param p2 This is the location where the value at the first Pair is moving.
     * @return The function returns the new board.
     */
    public Board movePiece(Pair p1, Pair p2) {
        this.status[p2.column][p2.row] = this.status[p1.column][p1.row];
        this.status[p1.column][p1.row] = 0;
        return this;
    }

    /**
     * numCheckers counts the number of checkers of the color of the player whose turn it is.
     * @param count This object counts the turn.
     * @return The method returns the number of checkers on the board of a particular color.
     */
    public int numCheckers(boolean count) {
        int numCheckers = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!count) if (this.status[i][j]==1) {
                    numCheckers++;
                } if (count) if (this.status[i][j]==2) {
                    numCheckers++;
                }
            }
        }
        return numCheckers;
    }

    /**
     * numCheckers counts the number of checkers in a line the direction of the Pair direction.
     * @param here This object represents the initial point.
     * @param direction This object represents the direction which the method checks. (It also checks backward.)
     * @return The method returns an integer of the number of checkers.
     */
    public int numCheckers(Pair here, Pair direction) {
        int count = 1;
        Pair p = here.addPair(direction);
        while (p.isValid()) {
            if (this.status[p.column][p.row] > 0) {
                count++;
            }
            p = p.addPair(direction);
        }
        p = here.addPair(direction.flipDirection());
        while (p.isValid()) {
            if (this.status[p.column][p.row] > 0) {
                count++;
            }
            p = p.addPair(direction.flipDirection());
        }
        return count;
    }

    /**
     * isConnected counts to see the number of checkers connected from some checker.
     * @param gridConnected This object holds a grid of checkers already checked so they aren't double counted.
     * @param COMPASS This object holds a Pair for each direction.
     * @param start This object represents the starting Pair.
     * @return The method returns the amount of connected checkers.
     */
    public int isConnected(boolean[][] gridConnected, Pair[] COMPASS, Pair start) {
        // false = black's turn


        Pair p;
        int numCheckers = 0;
        int us = this.status[start.column][start.row];

        for (Pair direction : COMPASS) {
            p = start.addPair(direction);
            if (p.isValid() && this.status[p.column][p.row] == us && !gridConnected[p.column][p.row]) {

                gridConnected[p.column][p.row] = true;
                numCheckers += 1 + this.isConnected(gridConnected, COMPASS, p);

            }
        }
        return numCheckers;
    }

}
