public class Board {

    public int[][] status = new int[8][8];

    public Board() {
        for (int i=0; i<6; i++) {
            status[i+1][0] = 1;
            status[i+1][7] = 1;
            status[0][i+1] = 2;
            status[7][i+1] = 2; // 1 is black, 2 is white.

        }
    }

    public Board(int[][] board) {
        status = board;
    }

    public boolean equals(int[][] that) {
        boolean valid = true;
        for (int c = 0; c<8; c++) {
            for (int r = 0; r<8; r++) {
                if (this.status[c][r] != that[c][r]) valid = false;
            }
        }
        return valid;
    }

    public Pair startPair(boolean count) {

        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                if (!count) if (this.status[c][r]==1) return new Pair(c, r);
                if (count) if (this.status[c][r]==2) return new Pair(c, r);
            }

        }
        return null;
    }

    public Board movePiece(Pair p1, Pair p2) {
        this.status[p2.column][p2.row] = this.status[p1.column][p1.row];
        this.status[p1.column][p1.row] = 0;
        return this;
    }

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
