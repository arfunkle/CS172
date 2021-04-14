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

}
