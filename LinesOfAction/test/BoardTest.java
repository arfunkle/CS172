import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void constructorWorks(){
        Board a = new Board();
        int[][] b = { {0, 1, 1, 1, 1, 1, 1, 0},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {0, 1, 1, 1, 1, 1, 1, 0} };
        int[][] c = { {0, 2, 2, 2, 2, 2, 2, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 2, 2, 2, 2, 2, 2, 0} };
        assertEquals(false, a.equals(b));
        assertEquals(true, a.equals(c));
    }


    @Test
    void numCheckersPairsWorks() {
        Board a = new Board();
        assertEquals(6,a.numCheckers(new Pair(1,0), new Pair(1,0)));
        assertEquals(2,a.numCheckers(new Pair(1,0), new Pair(0,1)));
        assertEquals(2,a.numCheckers(new Pair(1,0), new Pair(1,1)));
    }

    @Test
    void isConnectedWorks() {
        boolean[][] gridConnected = new boolean[8][8];
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
        Pair start1 = new Pair(0,3);
        Board board1 = new Board();
        Pair start2 = new Pair(3,5);
        int[][] grid2 = { {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 2, 2, 2, 2, 2, 2, 0} };
        Board board2 = new Board(grid2);
        assertEquals(6, board1.isConnected(gridConnected, COMPASS, start1));
        assertEquals(12, board2.isConnected(gridConnected, COMPASS, start2));

    }

}


